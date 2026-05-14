package com.reporta.report_service.services.impl;


import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;

@Service
public class JwtServiceImpl {

    @Value("${jwt.secret}")
    private String SECRET;

    private Key getKey() {
        byte[] keyBytes =
                Decoders.BASE64.decode(SECRET);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        return extractAllClaims(token)
                .getSubject();
    }

    public boolean isTokenValid(String token) {
        System.out.println("Validando token: " + token);
        try {
            extractAllClaims(token);
            return true;
        } catch (JwtException e) {
            System.out.println("Token inválido: " + token);
            return false;
        }
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
