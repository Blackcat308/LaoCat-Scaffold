package org.laocat.controller;

import lombok.AllArgsConstructor;
import org.laocat.entity.UserInfo;
import org.laocat.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

}
