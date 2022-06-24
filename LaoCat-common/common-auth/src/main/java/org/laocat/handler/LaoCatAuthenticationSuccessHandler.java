package org.laocat.handler;

import cn.hutool.log.StaticLog;
import com.alibaba.fastjson.JSON;
import org.laocat.auth.JwtRedisEnum;
import org.laocat.auth.JwtUtil;
import org.laocat.config.SecurityProperties;
import org.laocat.core.response.structure.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.laocat.constant.AuthConstant.*;

/**
 * @author LaoCat
 * @date 2022/6/15
 * @description 成功处理器
 */
public class LaoCatAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * @description: 构造token  but 因为用了 security permission
     * @author: LaoCat
     * @date: 2022/6/24
     * @returnType: void
     * @see SecurityExpressionRoot // #getAuthoritySet()
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        final String randomKey = jwtUtil.getRandomKey();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        final String token = jwtUtil.generateToken(username, randomKey);

        this.judgingMultiplePeople(username);

        this.toRedisStore(username, token, randomKey, authentication);

        response.setHeader(AUTHORIZATION, String.join(BEARER, token));
        response.setContentType(CONTENT_TYPE);
        response.getWriter().write(JSON.toJSONString(ResponseEntity.success(authentication)));
    }


    /**
     * @return void
     * @author LaoCat
     * @date 2022/6/16
     * @description 判断是否开启允许多人同账号同时在线，若不允许的话则先删除之前的
     */
    private void judgingMultiplePeople(String username) {
        if (securityProperties.isAllowMultiplePeople()) {
            // T掉同账号已登录的用户token信息
            batchDel(JwtRedisEnum.getTokenKey(username, "*"));
            // 删除同账号已登录的用户认证信息
            batchDel(JwtRedisEnum.getAuthenticationKey(username, "*"));
        }
    }

    /**
     * @return void
     * @author LaoCat
     * @date 2022/6/16
     * @description 将token 、 authenticationKey 入redis
     */
    private void toRedisStore(String username, String token, String randomKey, Authentication authentication) {
        redisTemplate.opsForValue().set(JwtRedisEnum.getTokenKey(username, randomKey),
                token,
                securityProperties.getEffectiveTime(),
                TimeUnit.SECONDS);

        redisTemplate.opsForValue().set(JwtRedisEnum.getAuthenticationKey(username, randomKey),
                authentication,
                securityProperties.getEffectiveTime(),
                TimeUnit.SECONDS
        );
    }

    /**
     * @return void
     * @author LaoCat
     * @date 2022/6/16
     * @description 批量删除redis的key
     */
    private void batchDel(String key) {
        Set<String> set = redisTemplate.keys(key);
        for (String keyStr : Objects.requireNonNull(set)) {
            StaticLog.info("keyStr{}", keyStr);
            redisTemplate.delete(keyStr);
        }
    }
}
