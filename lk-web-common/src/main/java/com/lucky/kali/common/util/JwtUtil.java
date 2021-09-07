package com.lucky.kali.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Calendar;

/**
 * @author elliot
 */
@Slf4j
public class JwtUtil {

    private static Algorithm alg;
    private static JWTVerifier verifier;

    private static final String SECRET = "U0JBUDud82frFt8YHk9uZz0yNjQ1NA==";
    private static final String ISSUER = "Elliot";
    private static final String PARAM_KEY = "springCloudJwt";
    private static final String PARAM_DATE = DateUtil.getNow();

    public static void init() {
        alg = Algorithm.HMAC384(SECRET);
        verifier = JWT.require(alg).withIssuer(ISSUER).build();
    }

    /**
     * 生成签名
     *
     * @param claim 用户名
     * @return token字符串
     */
    public static String createToken(String claim) {
        return JWT.create()
                .withIssuer(ISSUER)
                .withClaim(PARAM_KEY, claim)
                .withIssuedAt(LocalDateTimeUtil.localDateTimeToDate(LocalDateTime.now()))
                .withClaim(PARAM_DATE, Calendar.getInstance().getTimeInMillis())
                .sign(alg);
    }

    /**
     * 校验token是否正确
     *
     * @param token token字符串
     * @return true｜false
     */
    public static Boolean verify(String token) {
        try {
            return verifier.verify(token) != null;
        } catch (Exception e) {
            log.error("token 认证失败", e);
        }
        return false;
    }

    /**
     * 获取token中的信息
     *
     * @param token token字符串
     * @return token中的信息
     */
    public static String getClaim(String token) {
        return verifier.verify(token).getClaim(PARAM_KEY).asString();
    }

}
