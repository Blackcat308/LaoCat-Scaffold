package org.laocat.user.feign;

import org.laocat.user.vo.UserInfoVO;
import org.laocat.user.vo.UserRoleVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author LaoCat
 * @date 2022/6/15
 * @description 用户请求api
 */
@FeignClient(value = "LaoCat-user", contextId = "LaoCat-user")
public interface UserInfoFeignClient {

    @GetMapping("userInfo/userInfoByUserName")
    UserInfoVO loadUserInfoByUserName(@RequestParam("username") String username);


    @GetMapping("userRole/userRoleByUserId")
    List<UserRoleVO> loadUserRoleByUserId(@RequestParam("userId") String userId);
}
