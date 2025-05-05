package com.bugra.full_stack_login_app.controller;



import com.bugra.full_stack_login_app.request.UsernamePasswordRequest;
import com.bugra.full_stack_login_app.responses.UserResponseMessage;
import com.bugra.full_stack_login_app.service.AuthService;
import com.bugra.full_stack_login_app.service.CookieService;
import com.bugra.full_stack_login_app.service.user_services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;




@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500" ,allowCredentials = "true")
public class AuthController {


    private final UserService userService;
    private final AuthService authService;
    private final CookieService cookieService;
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AuthController(UserService userService,AuthService authService, CookieService cookieService) {
        this.userService = userService;
        this.authService = authService;
        this.cookieService = cookieService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerNewUser(@RequestBody UsernamePasswordRequest request){
        String response = userService.saveNewUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsernamePasswordRequest request , HttpServletResponse response){
        try{
            String token = authService.loginAndGenerateToken(request);
            logger.debug("Extracted JWT token:  {}" ,token);
            if(token == null){ throw new Exception("Token is null");}
            ResponseCookie responseCookie = cookieService.createResponseCookie("JWT", token);
            response.addHeader("Set-Cookie" , responseCookie.toString());
            return new ResponseEntity<>(UserResponseMessage.LOGIN_SUCCESSFUL ,HttpStatus.OK);
        }catch (Exception e){
            logger.warn("Something went wrong: {}",e.getMessage());
            return new ResponseEntity<>(UserResponseMessage.USER_NOT_FOUND, HttpStatus.UNAUTHORIZED);
        }

    }
}
