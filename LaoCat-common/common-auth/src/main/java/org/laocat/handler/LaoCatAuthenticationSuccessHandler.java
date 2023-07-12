package org.laocat.handler;

import com.alibaba.fastjson2.JSON;
import org.laocat.auth.JwtRedisEnum;
import org.laocat.auth.JwtUtil;
import org.laocat.config.SecurityProperties;
import org.laocat.core.response.structure.ResponseEntity;
import org.laocat.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

import static org.laocat.constant.AuthConstant.*;

/**
 * @author LaoCat
 * @date 2022/6/15
 * @description 成功处理器
 */
@Component
public class LaoCatAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private RedisUtil redisUtil;
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

        final String token = jwtUtil.generateToken(JSON.toJSONString(userDetails), randomKey);

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
        redisUtil.set(JwtRedisEnum.getTokenKey(username, randomKey), token, securityProperties.getEffectiveTime());

        redisUtil.set(JwtRedisEnum.getAuthenticationKey(username, randomKey), authentication, securityProperties.getEffectiveTime());
    }

    /**
     * @return void
     * @author LaoCat
     * @date 2022/6/16
     * @description 批量删除redis的key
     */
    private void batchDel(String key) {
        Set<String> keys = redisUtil.keys(key);

        redisUtil.del(keys.toArray(new String[0]));
    }
}
