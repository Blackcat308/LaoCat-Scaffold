package org.laocat.handler;

import cn.hutool.log.StaticLog;
import com.alibaba.fastjson.JSON;
import org.laocat.auth.GenerateModelMap;
import org.laocat.auth.JwtRedisEnum;
import org.laocat.propertiess.SecurityProperties;
import org.laocat.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author LaoCat
 * @date 2022/6/15
 * @description 成功处理器
 */
public class LaoCatAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        final String randomKey = jwtUtil.getRandomKey();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        StaticLog.info("username：{}", username);
        final String token = jwtUtil.generateToken(username, randomKey);
        StaticLog.info("登录成功！");
        // 判断是否开启允许多人同账号同时在线，若不允许的话则先删除之前的
        if (securityProperties.isAllowMultiplePeople()) {
            // T掉同账号已登录的用户token信息
            batchDel(JwtRedisEnum.getTokenKey(username, "*"));
            // 删除同账号已登录的用户认证信息
            batchDel(JwtRedisEnum.getAuthenticationKey(username, "*"));
        }
        // 存到redis
        redisTemplate.opsForValue().set(JwtRedisEnum.getTokenKey(username, randomKey),
                token,
                securityProperties.getEffectiveTime(),
                TimeUnit.SECONDS);
        //将认证信息存到redis，方便后期业务用，也方便 JwtAuthenticationTokenFilter用
        redisTemplate.opsForValue().set(JwtRedisEnum.getAuthenticationKey(username, randomKey),
                JSON.toJSONString(authentication),
                securityProperties.getEffectiveTime(),
                TimeUnit.SECONDS
        );
        ModelMap modelMap = GenerateModelMap.generateMap(HttpStatus.OK.value(), "登陆成功");
        modelMap.put("currentUser", authentication);
        response.setHeader("Authorization", "Bearer " + token);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(modelMap));
    }

    /**
     * 批量删除redis的key
     *
     * @param key：redis-key
     */
    private void batchDel(String key) {
        Set<String> set = redisTemplate.keys(key);
        for (String keyStr : Objects.requireNonNull(set)) {
            StaticLog.info("keyStr{}", keyStr);
            redisTemplate.delete(keyStr);
        }
    }
}
