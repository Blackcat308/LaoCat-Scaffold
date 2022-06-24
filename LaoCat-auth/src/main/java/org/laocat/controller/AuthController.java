package org.laocat.controller;

import lombok.AllArgsConstructor;
import org.laocat.auth.JwtRedisEnum;
import org.laocat.auth.JwtUtil;
import org.laocat.core.response.structure.ResponseEntity;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
     * @see SecurityExpressionRoot // #getAuthoritySet()
     * @description: 判断是否有自定义权限
     * @author: LaoCat
     * @date: 2022/6/23
     * @returnType: org.laocat.core.response.structure.ResponseEntity<java.lang.String>
     */
    @GetMapping("/custom")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> custom() {
        return ResponseEntity.success("您有admin权限！");
    }
}
