package org.laocat.auth;

import org.laocat.handler.LaoCatAuthenticationFailureHandler;
import org.laocat.handler.LaoCatAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author LaoCat
 * @date 2022/6/15
 * @description 身份认证管理
 */
@Configuration
public class LaoCatAuthenticationHandlerConfig {


    @Bean(name = "authenticationSuccessHandler")
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new LaoCatAuthenticationSuccessHandler();
    }

    @Bean(name = "authenticationFailureHandler")
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new LaoCatAuthenticationFailureHandler();
    }
}
