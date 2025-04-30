package com.bugra.full_stack_login_app.service;

import com.bugra.full_stack_login_app.model.User;
import com.bugra.full_stack_login_app.request.UsernamePasswordRequest;
import com.bugra.full_stack_login_app.security.JWTokenProvider;
import com.bugra.full_stack_login_app.service.user_services.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;
    private final JWTokenProvider tokenProvider;

    public AuthService(UserService userService, JWTokenProvider tokenProvider) {
        this.userService = userService;
        this.tokenProvider = tokenProvider;
    }

    public String loginAndGenerateToken(UsernamePasswordRequest request) {
        String token;
        User user = userService.getByUserName(request.getUsername());

        if(user != null){
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());

            if(authToken.isAuthenticated()){
                token = tokenProvider.generateToken(user.getUsername());
                return token;
            }
        }

        return null;
    }
}
