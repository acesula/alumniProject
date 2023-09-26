package com.alumni.project.security.utils;


import com.alumni.project.security.model.AuthUserDetail;
import com.alumni.project.security.properties.ApplicationProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
    private final ApplicationProperties applicationProperties;

    public String getUserIdFromToken(String jwtToken) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody()
                .getSubject();
    }


    public boolean validateJwtToken(String jwtToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(jwtToken);
            return true;
        } catch (SecurityException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    public String generateTokenForUser(AuthUserDetail authUserDetail){
        return Jwts.builder()
                .setClaims(new HashMap<>() {{
                    put("username", authUserDetail.getUsername());
                    put("email", authUserDetail.getEmail());
                    put("role", authUserDetail.getRole());
                }})
                .setSubject(authUserDetail.getId().toString())
                .setIssuedAt(java.util.Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(applicationProperties.getJwt().getValidityInMinutes(), ChronoUnit.MINUTES)))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSigningKey() {
        byte[] signingKey = Decoders.BASE64.decode(applicationProperties.getJwt().getKey());
        return Keys.hmacShaKeyFor(signingKey);
    }
}
