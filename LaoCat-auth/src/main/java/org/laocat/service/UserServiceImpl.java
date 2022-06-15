package org.laocat.service;

import lombok.AllArgsConstructor;
import org.laocat.auth.MD5Util;
import org.laocat.core.utils.VoUtils;
import org.laocat.user.feign.UserInfoFeignClient;
import org.laocat.user.vo.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author LaoCat
 * @date 2022/6/15
 */
@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserInfoFeignClient userInfoFeignClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfoVO userInfo = userInfoFeignClient.loadUserInfoByUserName(username);

        if (Objects.isNull(userInfo)) {
            throw new UsernameNotFoundException("用户不存在！");
        }

        if (!userInfo.getPassword().equals(MD5Util.encode(this.getPasswordByRequest()))) {
            throw new RuntimeException("密码不正确！");
        }

        // 暂未存放权限相关信息  后续补充 TODO
        return VoUtils.copy(userInfo, UserInfoDetails.class);
    }

    /**
     * @return java.lang.String
     * @author LaoCat
     * @date 2022/6/15
     * @description 获取 password 从request
     */
    private String getPasswordByRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        return request.getParameter("password");
    }

}
