package org.laocat.auth.service.sms;

import lombok.RequiredArgsConstructor;
import org.laocat.handler.LaoCatAuthenticationFailureHandler;
import org.laocat.handler.LaoCatAuthenticationSuccessHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @description: @see
 * @author: LaoCat
 * @date: 2023/7/11
 */
@Component
@RequiredArgsConstructor
public class LaoCatSmsAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final LaoCatAuthenticationSuccessHandler authenticationSuccessHandler;
    private final LaoCatAuthenticationFailureHandler authenticationFailureHandler;

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        LaoCatSmsAuthenticationFilter smsAuthenticationFilter = new LaoCatSmsAuthenticationFilter();
        smsAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));

        smsAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        smsAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);

        builder.addFilterBefore(smsAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
