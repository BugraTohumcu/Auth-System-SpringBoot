package com.bugra.full_stack_login_app.service;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import java.util.Arrays;

@Service
public class CookieService {

    public String getToken(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();

       if(cookies !=null){
           return Arrays.stream(cookies)
                   .filter(n -> n.getName().equals("JWT"))
                   .map(Cookie::getValue)
                   .findFirst()
                   .orElse(null);
       }
        return null;
    }


    public ResponseCookie createResponseCookie(String name, String value){
        return ResponseCookie.from(name,value)
                .httpOnly(true)
                .sameSite("None")
                .secure(true)
                .path("/")
                .maxAge(60*60*24)
                .build();
    }

}
