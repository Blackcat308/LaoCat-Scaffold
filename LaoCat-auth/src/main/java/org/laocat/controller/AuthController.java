package org.laocat.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "Token相关")
public class AuthController {

    /**
     * @description: 跳转login登录页面
     * @author: LaoCat
     * @date: 2022/6/23
     * @returnType: java.lang.String
     */
    @GetMapping("/login")
    @ApiOperation(value = "跳转login登录页面", notes = "跳转login登录页面")
    public String login() {
        return "login";
    }

    /**
     * @description: 判断是否有admin角色
     * @author: LaoCat
     * @date: 2022/6/23
     * @returnType: org.laocat.core.response.structure.ResponseEntity<java.lang.String>
     * @see SecurityExpressionRoot // #getAuthoritySet()
     */
    @GetMapping("/custom")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "判断是否有admin角色", notes = "判断是否有admin角色")
    public ResponseEntity<String> custom() {
        return ResponseEntity.success("您有admin权限！");
    }

    /**
     * @description: 判断是否有test角色
     * @author: LaoCat
     * @date: 2022/6/29
     * @returnType: org.laocat.core.response.structure.ResponseEntity<java.lang.String>
     * @see SecurityExpressionRoot // #getAuthoritySet()
     */
    @GetMapping("/test")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_TEST')")
    @ApiOperation(value = "判断是否有test角色", notes = "判断是否有test角色")
    public ResponseEntity<String> test() {
        return ResponseEntity.success("您有test权限！");
    }
}
