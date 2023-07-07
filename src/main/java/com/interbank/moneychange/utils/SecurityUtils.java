package com.interbank.moneychange.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;

import java.util.Map;

import static io.jsonwebtoken.Jwts.*;
import static io.jsonwebtoken.SignatureAlgorithm.HS256;

public class SecurityUtils {

    private static final String TOKEN_KEY   = "ITBK_2023";

    private SecurityUtils() {

    }

    public static String generateTokenWithoutExpiration(String value) {
        return setterBodyToken(value).compact();
    }

    public static boolean validateToken(String token) {
        token = getToken(token);
        try {
            getClaims(token);
            return true;
        } catch (ExpiredJwtException ignored) {
            return false;
        }
    }

    public static String decryptToken(String token) {
        return getClaims(getToken(token)).getSubject();
    }

    private static Map<String, Object> initClaims(String name) {
        return claims().setSubject(name);
    }

    private static JwtBuilder setterBodyToken(String value){
        return builder()
                .setClaims(initClaims(value))
                .signWith(HS256, TOKEN_KEY);
    }

    private static Claims getClaims(String token) {
        return parser().setSigningKey(TOKEN_KEY).parseClaimsJws(token).getBody();
    }

    public static String getToken(String token) {
        return token.replace("Bearer ", "");
    }
}
