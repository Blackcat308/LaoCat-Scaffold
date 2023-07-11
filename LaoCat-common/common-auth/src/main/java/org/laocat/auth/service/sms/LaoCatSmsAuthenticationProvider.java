package org.laocat.auth.service.sms;

import cn.hutool.core.lang.Assert;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @description: @see
 * @author: LaoCat
 * @date: 2023/7/11
 */
@Component
@RequiredArgsConstructor
public class LaoCatSmsAuthenticationProvider implements AuthenticationProvider {
    private final SmsUserServiceImpl smsUserService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.isInstanceOf(LaoCatSmsAuthenticationToken.class, authentication,
                "LaoCatSmsAuthenticationToken.onlySupports Only SmsAuthenticationToken is supported");
        LaoCatSmsAuthenticationToken smsAuthenticationToken = (LaoCatSmsAuthenticationToken) authentication;

        String phone = (String) smsAuthenticationToken.getPrincipal();
        String verificationCode = (String) smsAuthenticationToken.getCredentials();

        UserDetails userDetails = smsUserService.loadUserByUsername(phone);

        return new LaoCatSmsAuthenticationToken(userDetails.getAuthorities(), userDetails, verificationCode);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(LaoCatSmsAuthenticationToken.class);
    }
}
