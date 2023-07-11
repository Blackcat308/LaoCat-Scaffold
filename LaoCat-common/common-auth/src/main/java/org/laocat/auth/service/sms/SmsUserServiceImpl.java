package org.laocat.auth.service.sms;

import lombok.AllArgsConstructor;
import org.laocat.auth.service.UserInfoDetails;
import org.laocat.core.exception.VerificationCodeErrException;
import org.laocat.user.feign.UserInfoFeignClient;
import org.laocat.user.vo.UserInfoVO;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.laocat.constant.AuthConstant.REQ_PARAM_VERIFICATION_CODE_KEY;
import static org.laocat.core.exception.ErrMsgConstants.VERIFICATION_CODE_ERR;

/**
 * @author LaoCat
 * @date 2022/6/15
 */
@Service
@AllArgsConstructor
public class SmsUserServiceImpl implements UserDetailsService {

    private final UserInfoFeignClient userInfoFeignClient;

    /**
     * @return org.springframework.security.core.userdetails.UserDetails
     * @author LaoCat
     * @date 2022/6/16
     * @description 根据用户名获取用户信息 注意用户名要全局唯一
     */
    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        UserInfoVO userInfo = userInfoFeignClient.loadUserInfoByUserPhone(phone);

        if (Objects.isNull(userInfo)) {
            throw new UsernameNotFoundException("用户不存在！");
        }
        // 这里可以选择从缓存中拿出该用户手机号的验证码然后进行校验
        if (!"1234".equals(this.getVerificationCodeByRequest())) {
            throw new VerificationCodeErrException(VERIFICATION_CODE_ERR);
        }

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
                .authorities(
                        userInfoFeignClient.loadUserRoleByUserId(userInfo.getId())
                                .stream()
                                .map(userRole -> new SimpleGrantedAuthority(userRole.getRoleCode()))
                                .collect(Collectors.toSet())
                )
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();
    }

    /**
     * @return java.lang.String
     * @author LaoCat
     * @date 2022/6/15
     * @description 获取 password 从request
     */
    private String getVerificationCodeByRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        return request.getParameter(REQ_PARAM_VERIFICATION_CODE_KEY);
    }

}
