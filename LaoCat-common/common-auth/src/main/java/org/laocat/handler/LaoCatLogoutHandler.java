package org.laocat.handler;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import org.laocat.auth.JwtRedisEnum;
import org.laocat.auth.JwtUtil;
import org.laocat.core.response.structure.ResponseEntity;
import org.laocat.core.response.structure.ResponseEntityEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.laocat.constant.AuthConstant.*;
import static org.laocat.core.response.structure.ResponseEntityEnum.LOGOUT_SUCCESS;

/**
 * @description: 用后置处理 解决登出 @不推荐生产使用
 * @author: LaoCat
 * @date: 2022/6/24
 */
@Component
public class LaoCatLogoutHandler implements LogoutHandler {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    @SneakyThrows
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String authHeader = request.getHeader(AUTHORIZATION);
        String authToken = authHeader.substring(BEARER.length());

        String randomKey = jwtUtil.getMd5KeyByToken(authToken);
        String username = jwtUtil.getUsernameByToken(authToken);

        redisTemplate.delete(JwtRedisEnum.getTokenKey(username, randomKey));
        redisTemplate.delete(JwtRedisEnum.getAuthenticationKey(username, randomKey));

        response.setContentType(CONTENT_TYPE);
        response.getWriter().write(JSON.toJSONString(ResponseEntity.success(LOGOUT_SUCCESS)));
    }
}
