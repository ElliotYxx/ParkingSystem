package com.demo.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;

/**
 * JWT 工具类
 * @Author Sheva
 */
public class JwtUtil {

    public static final String TOKEN_LOGIN_NAME = "username";
    public static final String TOKEN_LOGIN_ID = "userId";
    public static final String TOKEN_SUCCESS = "success:";
    public static final String TOKEN_FAIL = "fail:";

    /**
     * 过期时间为一天
     */
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000;

    /**
     * token 私钥
     */
    private static final String TOKEN_SECRET = "joijsdfjlsjfljfljl5135313135";

    /**
     * 生成签名
     */
    public static String createToken(String username, String userId) {
        // 过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        // 私钥以及加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        // 设置头信息
        HashMap<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        // 附带username和userId生成签名
        return JWT.create()
                // 头
                .withHeader(header)
                .withClaim(TOKEN_LOGIN_NAME, username)
                .withClaim(TOKEN_LOGIN_ID, userId)
                // 过期时间
                .withExpiresAt(date)
                .sign(algorithm);

    }

    /**
     * 验证token
     */
    public static String verifyToken(String token) {
        String result = TOKEN_SUCCESS;
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            result += jwt.getClaims().get(TOKEN_LOGIN_NAME).asString();
            // 如果成功 返回 success：username
            return result;
        } catch (Exception e) {
            return TOKEN_FAIL + e.getMessage();
        }
    }
}
