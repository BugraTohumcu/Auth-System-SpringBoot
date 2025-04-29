package com.bugra.full_stack_login_app.security;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.function.Function;

public interface JwtTokenService {

    <T> T extractClaims(String token, Function<Claims,T> resolver);
    Claims extractAllClaims(String token);
    String extractUsername(String token);
    Date extractExpiration(String token);
    boolean isExpired(String token);
    boolean validateToken(String token , UserDetails userDetails);
}
