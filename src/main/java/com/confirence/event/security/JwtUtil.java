package com.confirence.event.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    // 🔒 Use a strong secret key (at least 256 bits for HS256)
    private final String SECRET_KEY = "mySecretKey12345mySecretKey12345mySecretKey12345"; // Must be at least 32 characters
    private final long JWT_EXPIRATION = 1000 * 60 * 60 * 10; // 10 hours

    // Create a SecretKey from the secret string
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    // Generate token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Extract username from token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extract any claim
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Validate token
    public boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    // Extract all claims (FIXED METHOD)
    private Claims extractAllClaims(String token) {
        return Jwts.parser()  // Changed from parser() to parserBuilder()
                .setSigningKey(getSigningKey())  // Use SecretKey instead of String
                .build()  // Build the parser
                .parseClaimsJws(token)  // Now this method exists
                .getBody();
    }

    // Check if token is expired
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
}
