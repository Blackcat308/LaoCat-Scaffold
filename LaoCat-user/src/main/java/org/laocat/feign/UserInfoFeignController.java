package org.laocat.feign;

import lombok.AllArgsConstructor;
import org.laocat.core.utils.VoUtils;
import org.laocat.service.UserInfoService;
import org.laocat.service.UserRoleAssociationService;
import org.laocat.user.feign.UserInfoFeignClient;
import org.laocat.user.vo.UserInfoVO;
import org.laocat.user.vo.UserRoleVO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author LaoCat
 * @date 2022/6/15
 * @description TODO
 */
@Component
@AllArgsConstructor
public class UserInfoFeignController implements UserInfoFeignClient {

    private final UserInfoService userInfoService;
    private final UserRoleAssociationService userRoleAssociationService;

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

    /**
     * @return org.laocat.user.vo.UserInfoVO
     * @author LaoCat
     * @date 2022/6/15
     * @description 内部调用 暂采用依赖方式
     */
    @Override
    public List<UserRoleVO> loadUserRoleByUserId(String userId) {
        return VoUtils.copy(userRoleAssociationService.selectUserRolesByUserId(userId), UserRoleVO.class);
    }

    @Override
    public UserInfoVO loadUserInfoByUserPhone(String phone) {
        return VoUtils.copy(userInfoService.loadUserInfoByUserPhone(phone), UserInfoVO.class);
    }
}
