package com.warriors.easyStock.Security.jwt;

import com.warriors.easyStock.Security.details.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtProvider {
        @Value("${security.jwt.token.secret-key}")
    private String secret;

    @Value("${security.jwt.token.expire-length}")
    private int expiration;

    public String generateToken(Authentication authentication) {

        UserDetailsImpl usuarioLogueado = (UserDetailsImpl) authentication.getPrincipal();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>> "+usuarioLogueado);

        List<String> roles = usuarioLogueado.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return Jwts.builder()
                .setSubject(usuarioLogueado.getUsername())
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 180))
                .signWith(getSecret(secret))
                .compact();
    }


    public String getNombreUsuarioFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(getSecret(secret)).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSecret(secret)).build().parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            log.error("token mal formado");
        } catch (UnsupportedJwtException e) {
            log.error("token no soportado");
        } catch (ExpiredJwtException e) {
            log.error("token expirado");
        } catch (IllegalArgumentException e) {
            log.error("token vacío");
        } catch (SignatureException e) {
            log.error("fail en la firma");
        }
        return false;
    }

    private Key getSecret(String secret) {
        byte[] secretBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(secretBytes);
    }
}
