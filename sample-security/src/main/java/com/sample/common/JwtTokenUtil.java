package com.sample.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * token 工具类
 */
@Component
public class JwtTokenUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

    private static String secret;

    private static Long expiration;

    /**
     * 静态变量无法直接通过 @Value注入，需要通过辅助set函数
     *
     * @param s
     */
    @Value("${jwt.secret}")
    public void setSecret(String s) {
        secret = s;
    }

    @Value("${jwt.expiration}")
    public void setExpiration(long ex) {
        expiration = ex;
    }

    /**
     * 根据用户信息生成token
     *
     * @param username
     * @return
     */
    public static String generateToken(String username) {
        Map<String, Object> map = new HashMap<>();
        map.put(Claims.ISSUED_AT, new Date()); // 创建时间
        map.put(Claims.SUBJECT, username); // 负载：用户名
        return generateToken(map);
    }

    /**
     * 验证token
     *
     * @param token
     * @param username
     * @return
     */
    public static boolean validateToken(String token, String username) {
        Claims claims = parseToken(token);
        if (claims != null) {
            String tokenUsername = claims.getSubject();
            return tokenUsername.equals(username) && !isTokenExpired(token);
        }
        return false;
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     */
    public static Claims parseToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            LOGGER.info("JWT token解析失败：{}", token);
        }
        return claims;
    }

    /**
     * 判断token是否已经过期
     *
     * @param token
     * @return
     */
    public static boolean isTokenExpired(String token) {
        Claims claims = parseToken(token);
        if (claims != null) {
            System.out.println("claims:" + claims.toString());
            Date expireDate = claims.getExpiration();
            System.out.println("expireDate:" + expireDate);
            return expireDate.before(new Date());
        }
        return true;
    }

    /**
     * 从token获取用户名
     *
     * @param token
     * @return
     */
    public static String getUsernameFromToken(String token) {
        Claims claims = parseToken(token);
        if (claims != null) {
            return claims.getSubject();
        }
        return null;
    }

    /**
     * 刷新token
     *
     * @param token
     * @return
     */
    public static String refreshToken(String token) {
        Claims claims = parseToken(token);
        claims.setIssuedAt(new Date());
        return generateToken(claims);
    }

    private static Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }


    /**
     * 根据claims生成负载
     *
     * @param claims
     * @return
     */
    private static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }


}
