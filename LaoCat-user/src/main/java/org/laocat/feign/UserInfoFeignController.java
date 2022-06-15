package org.laocat.feign;

import lombok.AllArgsConstructor;
import org.laocat.core.utils.VoUtils;
import org.laocat.service.UserInfoService;
import org.laocat.user.feign.UserInfoFeignClient;
import org.laocat.user.vo.UserInfoVO;
import org.springframework.stereotype.Component;

/**
 * @author LaoCat
 * @date 2022/6/15
 * @description TODO
 */
@Component
@AllArgsConstructor
public class UserInfoFeignController implements UserInfoFeignClient {

    private final UserInfoService userInfoService;

    /**
     * @return org.laocat.user.vo.UserInfoVO
     * @author LaoCat
     * @date 2022/6/15
     * @description 内部调用 暂采用依赖方式
     */
    @Override
    public UserInfoVO loadUserInfoByUserName(String username) {
        return VoUtils.copy(userInfoService.loadUserInfoByUserName(username), UserInfoVO.class);
    }
}
