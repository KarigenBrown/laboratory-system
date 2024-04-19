package edu.bistu.utils;

import cn.hutool.core.util.IdUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.jackson.io.JacksonSerializer;
import io.jsonwebtoken.security.MacAlgorithm;
import lombok.SneakyThrows;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

public class JwtUtils {
    public static final Long JWT_TTL = 24 * 60 * 60 * 1_000L;
    public static final String JWT_KEY = "eyJhbGciOiJIUzI1NiJ9eyJqdGkiOiJjYWM2ZDVhZi1mNjVlLTQ0MDAtYjcxMi0zYWEwOGIyOTIwYjQiLCJzdWIiOiJzZyIsImlzcyI6InNnIiwiaWF0IjoxNjM4MTA2NzEyLCJleHAiOjE2MzgxMTAzMTJ9JVsSbkP94wuczb4QryQbAke3ysBDIL5ou8fWsbtebg";

    private JwtUtils() {
    }

    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtils.JWT_KEY);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "HmacSHA256");
    }

    public static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        MacAlgorithm macAlgorithm = Jwts.SIG.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date nowDate = new Date(nowMillis);
        if (ttlMillis == null) {
            ttlMillis = JwtUtils.JWT_TTL;
        }
        long expireMillis = nowMillis + ttlMillis;
        Date expireDate = new Date(expireMillis);
        return Jwts.builder()
                .id(uuid)
                .subject(subject)
                .issuer("karigen")
                .issuedAt(nowDate)
                .signWith(secretKey,macAlgorithm)
                .expiration(expireDate)
                .json(new JacksonSerializer<>(new ObjectMapper()));
    }

    public static String createJWT(String id, String subject, Long ttlMillis) {
        return getJwtBuilder(subject, ttlMillis, id).compact();
    }

    public static String createJWT(String subject) {
        return getJwtBuilder(subject, null, IdUtil.simpleUUID()).compact();
    }

    @SneakyThrows(Exception.class)
    public static Claims parseJWT(String jwt) {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()

                .parseSignedClaims(jwt)
                .getPayload();
    }
}
