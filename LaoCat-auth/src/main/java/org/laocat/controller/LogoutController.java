package org.laocat.controller;

import cn.hutool.log.StaticLog;
import org.laocat.auth.GenerateModelMap;
import org.laocat.auth.JwtRedisEnum;
import org.laocat.utils.JwtUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LaoCat
 * @date 2022/6/15
 * @description
 */
@RestController
public class LogoutController {


    private final StringRedisTemplate redisTemplate;
    private final JwtUtil jwtUtil;

    public LogoutController(StringRedisTemplate redisTemplate, JwtUtil jwtUtil) {
        this.redisTemplate = redisTemplate;
        this.jwtUtil = jwtUtil;
    }

    /**
     * 退出登录
     * 双清
     */
    @GetMapping("/jwtLogout")
    public ResponseEntity<ModelMap> logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        // 为嘛无需判断authHeader是否为null？ 因为Jwt过滤器已经判断过了。
        String authToken = authHeader.substring("Bearer ".length());
        String randomKey = jwtUtil.getMd5KeyByToken(authToken);
        String username = jwtUtil.getUsernameByToken(authToken);
        redisTemplate.delete(JwtRedisEnum.getTokenKey(username, randomKey));
        redisTemplate.delete(JwtRedisEnum.getAuthenticationKey(username, randomKey));
        StaticLog.info("删除{}成功", JwtRedisEnum.getTokenKey(username, randomKey));
        StaticLog.info("退出成功");
        ModelMap modelMap = GenerateModelMap.generateMap(HttpStatus.OK.value(), "退出成功！");
        return ResponseEntity.ok(modelMap);
    }
}
