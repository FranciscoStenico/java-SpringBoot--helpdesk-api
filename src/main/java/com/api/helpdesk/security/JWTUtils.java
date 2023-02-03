package com.api.helpdesk.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(String email) {
        return Jwts.builder().setSubject(email).setExpiration(new Date(System.currentTimeMillis() + this.expiration))
                .signWith(SignatureAlgorithm.HS512, this.secret.getBytes()).compact();
    }

    public boolean validToken(String token) {
        Claims claims = this.getClaims(token);
        if (claims != null) {
            String username = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            Date currentTime = new Date(System.currentTimeMillis());

            return username != null && expirationDate != null && currentTime.before(expirationDate);
        }
        return false;
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(this.secret.getBytes()).parseClaimsJws(token).getBody();
        } catch (Exception error) {
            return null;
        }
    }

    public String getUsername(String token) {
        Claims claims = this.getClaims(token);
        return claims != null ? claims.getSubject() : null;
    }

}
