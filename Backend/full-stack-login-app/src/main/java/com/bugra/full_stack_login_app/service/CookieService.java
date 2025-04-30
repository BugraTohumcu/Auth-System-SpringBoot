package com.bugra.full_stack_login_app.service;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

@Service
public class CookieService {

    public String getToken(HttpServletRequest request){
        String token= null;
        Cookie[] cookies = request.getCookies();

       if(cookies != null){
           for(Cookie cookie : cookies){
               if(cookie.getName().equals("JWT")){
                   token = cookie.getValue();
                   break;
               }
           }
       }
        return  token;
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
