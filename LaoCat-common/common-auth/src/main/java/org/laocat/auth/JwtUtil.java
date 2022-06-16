package org.laocat.auth;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.laocat.config.SecurityProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import static org.laocat.constant.AuthConstant.*;

/**
 * @author LaoCat
 * @date 2022/6/15
 * @description JWT 工具类
 */
@Component
@AllArgsConstructor
public class JwtUtil {

    private final SecurityProperties securityProperties;

    /**
     * @return java.lang.String
     * @author LaoCat
     * @date 2022/6/15
     */
    public static String getRandomString(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(BASIC_STR.length());
            sb.append(BASIC_STR.charAt(number));
        }
        return sb.toString();
    }

    /**
     * @return java.lang.String
     * @author LaoCat
     * @date 2022/6/15
     */
    public String getUsernameByToken(String token) {
        return getClaimByToken(token).getSubject();
    }

    /**
     * @return java.util.Date
     * @author LaoCat
     * @date 2022/6/15
     */
    public Date getExpirationDateByToken(String token) {
        return getClaimByToken(token).getExpiration();
    }

    /**
     * @return java.lang.String
     * @author LaoCat
     * @date 2022/6/15
     * @description jwt claim
     */
    public String getPrivateClaimByToken(String token, String key) {
        return getClaimByToken(token).get(key).toString();
    }

    /**
     * @return java.lang.String
     * @author LaoCat
     * @date 2022/6/15
     */
    public String getMd5KeyByToken(String token) {
        return getPrivateClaimByToken(token, securityProperties.getUniqueTag());
    }

    /**
     * @return io.jsonwebtoken.Claims
     * @author LaoCat
     * @date 2022/6/15
     * @description jwt的payload部分
     */
    public Claims getClaimByToken(String token) {
        return Jwts.parser()
                .setSigningKey(generalKey())
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * @return java.lang.Boolean
     * @author LaoCat
     * @date 2022/6/15
     * @description 验证token是否失效
     */
    public Boolean isTokenExpired(String token) {
        try {
            final Date expiration = getExpirationDateByToken(token);
            return expiration.before(DateUtil.date());
        } catch (ExpiredJwtException expiredJwtException) {
            return true;
        }
    }

    /**
     * @return java.lang.String
     * @author LaoCat
     * @date 2022/6/15
     * @description 生成token  username + randomKey
     */
    public String generateToken(String userName, String randomKey) {
        Map<String, Object> claims = MapUtil.newHashMap();
        claims.put(securityProperties.getUniqueTag(), randomKey);
        return doGenerateToken(claims, userName);
    }


    public SecretKey generalKey() {
        byte[] decode = Base64.decode(securityProperties.getSecret());
        return new SecretKeySpec(decode, 0, decode.length, AES);
    }

    /**
     * @return java.lang.String
     * @author LaoCat
     * @date 2022/6/15
     * @description 构造token
     */
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        final DateTime createDateTime = DateUtil.date();
        final DateTime expirationDateTime = DateUtil.offsetSecond(createDateTime, securityProperties.getEffectiveTime().intValue());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(createDateTime)
                .setExpiration(expirationDateTime)
                .signWith(SignatureAlgorithm.HS512, generalKey())
                .compact();
    }


    public String getRandomKey() {
        return getRandomString(RANDOM_STR_LENGTH);
    }


    public String refreshToken(String token, String randomKey) {
        String refreshedToken;
        try {
            final Claims claims = getClaimByToken(token);
            refreshedToken = generateToken(claims.getSubject(), randomKey);
        } catch (Exception e1) {
            refreshedToken = null;
        }
        return refreshedToken;
    }


}