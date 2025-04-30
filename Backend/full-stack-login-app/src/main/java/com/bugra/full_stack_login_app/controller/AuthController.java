package com.bugra.full_stack_login_app.controller;


import com.bugra.full_stack_login_app.model.User;
import com.bugra.full_stack_login_app.request.UsernamePasswordRequest;
import com.bugra.full_stack_login_app.responses.UserResponseMessage;
import com.bugra.full_stack_login_app.security.JWTokenProvider;
import com.bugra.full_stack_login_app.service.user_services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500/LoginPage.html" ,allowCredentials = "true")
public class AuthController {


    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JWTokenProvider jwTokenProvider;

    public AuthController(UserService userService, AuthenticationManager authenticationManager, JWTokenProvider jwTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwTokenProvider = jwTokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerNewUser(@RequestBody UsernamePasswordRequest request){
        String response = userService.saveNewUser(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsernamePasswordRequest request , HttpServletResponse response){
        User user = userService.getByUserName(request.getUsername());

        if(user !=null ){
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
                    request.getPassword()));
            if(auth.isAuthenticated()){
                String token = jwTokenProvider.generateToken(request.getUsername());
                System.out.println("token from AuthController login method: "+token);

                ResponseCookie resCookie = ResponseCookie.from("JWT", token)
                        .httpOnly(true)
                        .sameSite("None")
                        .secure(true)
                        .path("/")
                        .maxAge(60*60*24)
                        .build();
                response.addHeader("Set-Cookie", resCookie.toString());

            }
            return new ResponseEntity<>(UserResponseMessage.LOGIN_SUCCESSFUL,HttpStatus.OK);
        }

        return new ResponseEntity<>(UserResponseMessage.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}
