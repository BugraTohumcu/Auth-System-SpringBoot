package com.bugra.full_stack_login_app.controller;


import com.bugra.full_stack_login_app.model.User;
import com.bugra.full_stack_login_app.request.UsernamePasswordRequest;
import com.bugra.full_stack_login_app.responses.UserResponseMessage;
import com.bugra.full_stack_login_app.security.JWTokenProvider;
import com.bugra.full_stack_login_app.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@CrossOrigin
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
    public ResponseEntity<String> login(@RequestBody UsernamePasswordRequest request){
        User user = userService.getByUserName(request.getUsername());

        if(user !=null ){
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
                    request.getPassword()));
            System.out.println(jwTokenProvider.generateToken(request.getUsername()));
            return new ResponseEntity<>(UserResponseMessage.LOGIN_SUCCESSFUL,HttpStatus.OK);
        }

        return new ResponseEntity<>(UserResponseMessage.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}
