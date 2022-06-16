package org.laocat.controller;

import org.laocat.core.response.structure.ResponseEntity;
import org.laocat.entity.UserInfo;
import org.laocat.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

/**
 * @author LaoCat
 * @date 2022/6/15
 * @description 用户信息 controller
 */
@RestController
@RequestMapping("userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * @return org.laocat.entity.UserInfo
     * @author LaoCat
     * @date 2022/6/15
     * @description 根据用户名获取用户信息
     */
    @GetMapping("userInfoByUserName")
    public UserInfo loadUserInfoByUserName(@RequestParam String username) {
        return userInfoService.loadUserInfoByUserName(username);
    }

    /**
     * @return org.laocat.entity.UserInfo
     * @author LaoCat
     * @date 2022/6/15
     * @description 新增用户
     */
    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody UserInfo userInfo) {
        if (Objects.nonNull(this.loadUserInfoByUserName(userInfo.getUsername()))) {
            throw new RuntimeException("该用户已存在");
        }

        userInfoService.register(userInfo);
        return ResponseEntity.success();
    }


}
