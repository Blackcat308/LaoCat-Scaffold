package org.laocat.auth.service;

import cn.hutool.core.collection.CollUtil;
import lombok.AllArgsConstructor;
import org.laocat.auth.MD5Util;
import org.laocat.core.exception.PasswordErrException;
import org.laocat.user.feign.UserInfoFeignClient;
import org.laocat.user.vo.UserInfoVO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static org.laocat.constant.AuthConstant.REQ_PARAM_PASSWORD_KEY;
import static org.laocat.core.exception.ErrMsgConstants.PASSWORD_ERR;

/**
 * @author LaoCat
 * @date 2022/6/15
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    private final UserInfoFeignClient userInfoFeignClient;

    /**
     * @return org.springframework.security.core.userdetails.UserDetails
     * @author LaoCat
     * @date 2022/6/16
     * @description 根据用户名获取用户信息 注意用户名要全局唯一
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfoVO userInfo = userInfoFeignClient.loadUserInfoByUserName(username);

        if (Objects.isNull(userInfo)) {
            throw new UsernameNotFoundException("用户不存在！");
        }

        if (!userInfo.getPassword().equals(MD5Util.encode(this.getPasswordByRequest()))) {
            throw new PasswordErrException(PASSWORD_ERR);
        }

        // 暂未补齐权限相关信息  后续补充 TODO
        return this.buildUserDetails(userInfo);
    }

    /**
     * @return org.springframework.security.core.userdetails.UserDetails
     * @author LaoCat
     * @date 2022/6/16
     * @description 构造Spring Security UserDetails
     */
    private UserDetails buildUserDetails(UserInfoVO userInfo) {
        return UserInfoDetails.builder()
                .id(userInfo.getId())
                .username(userInfo.getUsername())
                .password(userInfo.getPassword())
                .roles(CollUtil.newArrayList("ROLE_ADMIN", "ROLE_CUSTOM"))
                .build();
    }

    /**
     * @return java.lang.String
     * @author LaoCat
     * @date 2022/6/15
     * @description 获取 password 从request
     */
    private String getPasswordByRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        return request.getParameter(REQ_PARAM_PASSWORD_KEY);
    }

}
