package com.bugra.full_stack_login_app.security;

import com.bugra.full_stack_login_app.service.CookieService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JWTokenProvider tokenProvider;
    private final UserDetailsService userDetailsService;
    private final CookieService cookieService;

    public JwtAuthFilter(JWTokenProvider tokenProvider, UserDetailsService userDetailsService, CookieService cookieService) {
        this.tokenProvider = tokenProvider;
        this.userDetailsService = userDetailsService;
        this.cookieService = cookieService;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();
        if(path.startsWith("/login") || path.startsWith("/register")){
            filterChain.doFilter(request,response);
            return;
        }
        String token = cookieService.getToken(request);
        String username;



    if (token != null) {
        try {
            username = tokenProvider.extractUsername(token);
            
            if(tokenProvider.shouldRefreshToken(token)) {
                token = tokenProvider.generateToken(username);
                ResponseCookie resCookie = cookieService.createResponseCookie("JWT", token);
                response.addHeader("Set-Cookie", resCookie.toString());
            }

            if(StringUtils.hasText(username) && SecurityContextHolder.getContext().getAuthentication()==null){
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if(userDetails != null && tokenProvider.validateToken(token,userDetails)){
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails.getUsername(),userDetails.getPassword(),userDetails.getAuthorities());

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (Exception e) {

            logger.error("Something went wrong " + e.getMessage());
        }
    }

    filterChain.doFilter(request,response);
}

}