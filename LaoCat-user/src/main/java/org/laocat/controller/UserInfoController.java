package org.laocat.controller;

import lombok.AllArgsConstructor;
import org.laocat.core.exception.UserAlreadyExistException;
import org.laocat.core.response.structure.ResponseEntity;
import org.laocat.entity.UserInfo;
import org.laocat.service.UserInfoService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

import static org.laocat.core.exception.ErrMsgConstants.USER_ALREADY_EXIST;

/**
 * @author LaoCat
 * @date 2022/6/15
 * @description 用户信息 controller
 */
@RestController
@RequestMapping("userInfo")
@AllArgsConstructor
public class UserInfoController {

    private final UserInfoService userInfoService;

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
     * @description 新增用户 == 注册用户
     */
    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody UserInfo userInfo) {
        if (Objects.nonNull(this.loadUserInfoByUserName(userInfo.getUsername()))) {
            throw new UserAlreadyExistException(USER_ALREADY_EXIST);
        }

        userInfoService.register(userInfo);
        return ResponseEntity.success();
    }


}
