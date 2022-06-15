package org.laocat.user.feign;

import org.laocat.user.vo.UserInfoVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author LaoCat
 * @date 2022/6/15
 * @description TODO
 */
@Configuration
@FeignClient(value = "LaoCat-user", contextId = "LaoCat-user")
public interface UserInfoFeignClient {

    @GetMapping("userInfo/userInfoByUserName")
    UserInfoVO loadUserInfoByUserName(@RequestParam String username);
}
