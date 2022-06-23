package org.laocat.controller;

import lombok.AllArgsConstructor;
import org.laocat.auth.JwtRedisEnum;
import org.laocat.auth.JwtUtil;
import org.laocat.core.response.structure.ResponseEntity;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import static org.laocat.constant.AuthConstant.AUTHORIZATION;
import static org.laocat.constant.AuthConstant.BEARER;

/**
 * @author LaoCat
 * @date 2022/6/15
 * @description Token 相关
 */
@Controller
@AllArgsConstructor
public class AuthController {

    private final StringRedisTemplate redisTemplate;
    private final JwtUtil jwtUtil;

    /**
     * @return org.laocat.core.response.structure.ResponseEntity<?>
     * @author LaoCat
     * @date 2022/6/16
     * @description 退出登录
     */
    @GetMapping("/logout")
    @ResponseBody
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String authHeader = request.getHeader(AUTHORIZATION);
        String authToken = authHeader.substring(BEARER.length());

        String randomKey = jwtUtil.getMd5KeyByToken(authToken);
        String username = jwtUtil.getUsernameByToken(authToken);

        redisTemplate.delete(JwtRedisEnum.getTokenKey(username, randomKey));
        redisTemplate.delete(JwtRedisEnum.getAuthenticationKey(username, randomKey));

        return ResponseEntity.success();
    }

    /**
     * @description: 跳转hello 页面
     * @author: LaoCat
     * @date: 2022/6/23
     * @returnType: java.lang.String
     */
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    /**
     * @description: 跳转login登录页面
     * @author: LaoCat
     * @date: 2022/6/23
     * @returnType: java.lang.String
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * @description: 判断是否有自定义权限
     * @author: LaoCat
     * @date: 2022/6/23
     * @returnType: org.laocat.core.response.structure.ResponseEntity<java.lang.String>
     */
    @GetMapping("/custom")
    @ResponseBody
    @PreAuthorize("hasRole('custom')")
    public ResponseEntity<String> custom() {
        return ResponseEntity.success("您有自定义权限！");
    }
}
