package com.bugra.full_stack_login_app.controller;

import com.bugra.full_stack_login_app.service.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TestController {

    private final CookieService cookieService;

    public TestController(CookieService cookieService) {
        this.cookieService = cookieService;
    }


    @GetMapping("/hello")
    public String hello(HttpServletRequest request){
        String token = cookieService.getToken(request);
        return "token:" + token;
    }
}
