package com.fleafair.Config;

import io.jsonwebtoken.*;
import java.util.Date;

/**
 * 生成和解密token
 */
public class JwtUtil {
    private static final String SECRET_KEY = "fleafair";

    // 生成token
    public static String createToken(Long userId) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)) //7天有效
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }


    //解析 token
    public static Integer parseToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY) // 密钥
                .parseClaimsJws(token)
                .getBody();
        return Integer.valueOf(claims.getSubject());
    }
}
