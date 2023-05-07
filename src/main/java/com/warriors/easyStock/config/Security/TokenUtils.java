package com.warriors.easyStock.config.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {
    private static final String ACCESS_TOKEN_SECRET = "$2a$10$ARmxWX9E2okWxujW04WF3e2k2AAIGBYmX7/RXfKOhL62o8WYRq3za";

    private static final Long tokenValidityInDays = 1_800L;


    public static String createToken(String nombre,String correo) {
        long expirationTime = tokenValidityInDays * 1_000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        Map<String, Object> extra = new HashMap<>();
        extra.put("nombre", nombre );

        return Jwts.builder()
                .setSubject(correo)
                .setExpiration(expirationDate)
                .addClaims(extra).
                signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String correo = claims.getSubject();

            return new UsernamePasswordAuthenticationToken(correo, null, Collections.emptyList());
        } catch (JwtException e) {
            return null;
        }
    }
}
