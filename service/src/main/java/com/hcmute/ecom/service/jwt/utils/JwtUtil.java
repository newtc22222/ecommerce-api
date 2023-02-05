package com.hcmute.ecom.service.jwt.utils;

import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "Yes, this is a SECRET KEY";

    public String getUserPhoneFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver) {
        final Claims claims = getAllClaimFromToken(token);
        return claimResolver.apply(claims);
    }

    private Claims getAllClaimFromToken(String token) {
        try {
            return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        }
        catch (SignatureException err) {
            throw new RuntimeException(err);
        }
        catch (ExpiredJwtException err) {
            throw new RuntimeException(err.getMessage());
        }
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String phone = getUserPhoneFromToken(token);
        return ( phone.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        Date expirationDate = getExpirationDateFromToken(token);
        return expirationDate.before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public String generateJwtToken(UserDetails userDetails) {
        long timestamp = System.currentTimeMillis();

        return Jwts.builder()
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + (1000 * 60 * 60 * 2)))
                .setSubject(userDetails.getUsername()) // Actually, it's a phone
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
