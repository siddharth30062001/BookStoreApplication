package com.bridgelabz.book_store.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenUtility {

    private static final String SECRET = "secret";
    private static final long EXPIRATION_TIME = 600_0000;

    public String createToken(Long userId,String emailId,  String role) {
        return JWT.create()
                .withClaim("userId", userId)
                .withClaim("emailId", emailId)
                .withClaim("role", role)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET));
    }


    public String getRoleFromToken(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET))
                .build()
                .verify(token)
                .getClaim("role")
                .asString();
    }

    public String getEmailFromToken(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET))
                .build()
                .verify(token)
                .getClaim("emailId")
                .asString();
    }

    public Long getUserIdFromToken(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET))
                .build()
                .verify(token)
                .getClaim("userId")
                .asLong();
    }


    // Method to extract the expiration date from the token

    public Date getExpirationFromToken(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SECRET))
                .build()
                .verify(token);

        return decodedJWT.getExpiresAt();
    }
}
