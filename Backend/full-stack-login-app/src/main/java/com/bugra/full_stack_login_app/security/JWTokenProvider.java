package com.bugra.full_stack_login_app.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

@Component
public class JWTokenProvider {


    private final String secretKey;
    public JWTokenProvider() {
        this.secretKey = generateSecretKey();
    }


    public String generateSecretKey(){
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey secretKey = keyGen.generateKey();
            System.out.println("Secret key: "+secretKey.toString());
            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }


    public String generateToken(String username){
        HashMap<String,Object> claims = new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*3))
                .signWith(getKey())
                .compact();
    }

    private Key getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
