package com.student.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    private SecretKey getSigningKey() {

        byte[] keyBytes =
                Decoders.BASE64.decode(secretKey);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    // =========================
    // GENERATE TOKEN
    // =========================
    public String generateToken(
            String email,
            String role) {

        Date now = new Date();

        Date expiration =
                new Date(
                        now.getTime() + jwtExpiration
                );

        return Jwts.builder()
                .subject(email)
                .claim("role", role)
                .issuedAt(now)
                .expiration(expiration)
                .signWith(getSigningKey())
                .compact();
    }

    // =========================
    // EXTRACT EMAIL
    // =========================
    public String extractEmail(String token) {

        return extractClaim(
                token,
                Claims::getSubject
        );
    }

    // =========================
    // EXTRACT ROLE
    // =========================
    public String extractRole(String token) {

        return extractClaim(
                token,
                claims ->
                        claims.get(
                                "role",
                                String.class
                        )
        );
    }

    // =========================
    // EXTRACT EXPIRATION
    // =========================
    public Date extractExpiration(String token) {

        return extractClaim(
                token,
                Claims::getExpiration
        );
    }

    // =========================
    // CHECK EXPIRATION
    // =========================
    public boolean isTokenExpired(String token) {

        return extractExpiration(token)
                .before(new Date());
    }

    // =========================
    // VALIDATE TOKEN
    // =========================
    public boolean isTokenValid(
            String token,
            String email) {

        String extractedEmail =
                extractEmail(token);

        return extractedEmail.equals(email)
                && !isTokenExpired(token);
    }

    // =========================
    // EXTRACT CLAIM
    // =========================
    private <T> T extractClaim(
            String token,
            Function<Claims, T> claimsResolver) {

        Claims claims =
                Jwts.parser()
                        .verifyWith(getSigningKey())
                        .build()
                        .parseSignedClaims(token)
                        .getPayload();

        return claimsResolver.apply(claims);
    }
}