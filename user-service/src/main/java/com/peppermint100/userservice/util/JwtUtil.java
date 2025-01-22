package com.peppermint100.userservice.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {
    @Value("${token.secret}")
    private String secret;

    final static long expirationTimeInMilliSeconds = 360000;

    public String createBearerTokenWithEmail(String email) {
        Instant now = Instant.now();
        Instant validity = now.plus(Duration.ofMillis(expirationTimeInMilliSeconds));

        String token = JWT.create()
                .withSubject(email)
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(validity))
                .sign(Algorithm.HMAC512(secret));

        return "Bearer " + token;
    }

    public Boolean validateToken(String token) {
        try {
            JWT.require(Algorithm.HMAC512(secret))
                    .build()
                    .verify(token);
            return true;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            log.error("유효하지 않은 토큰입니다. " + token);
            return false;
        }
    }

    public Date getExpirationFromToken(String token) {
        DecodedJWT jwt = JWT.require(Algorithm.HMAC512(secret))
                .build()
                .verify(token);
        return jwt.getExpiresAt();
    }

    public String getEmailFromToken(String token) {
        DecodedJWT jwt = JWT.require(Algorithm.HMAC512(secret))
                .build()
                .verify(token);
        return jwt.getSubject();
    }
}
