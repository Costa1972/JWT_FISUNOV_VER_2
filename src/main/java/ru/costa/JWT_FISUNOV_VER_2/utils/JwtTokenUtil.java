package ru.costa.JWT_FISUNOV_VER_2.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Costa Vashchuk
 * Утилитный класс для работы с токеном
 */
@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret = "secret";
    @Value("${jwt.lifetime}")
    private Duration jwtLifetime = Duration.ofDays(30);


    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        // Получаем роли в виде строк из Authorities и складываем в List
        List<String> roleList = userDetails
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        claims.put("roles", roleList);
        Date issuedTime = new Date();
        Date expiredTime = new Date(issuedTime.getTime() + jwtLifetime.toMillis());
        // Генерируем и возвращаем токен
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(issuedTime)
                .setExpiration(expiredTime)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }


    // вытаскиваем из тела токена username
    public String getUsername(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    // вытаскиваем из тела токена роли
    public List<String> getRoles(String token) {
        return getAllClaimsFromToken(token).get("roles", List.class);
    }

    // метод разбора токена
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret) // добавляем строку с "секретом" для азбора токена
                .parseClaimsJws(token)
                .getBody();
    }

}
