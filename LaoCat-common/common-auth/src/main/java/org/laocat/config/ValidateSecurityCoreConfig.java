package org.laocat.config;


import org.laocat.auth.MD5Util;
import org.laocat.auth.manager.AuthorizeConfigManager;
import org.laocat.auth.service.UserServiceImpl;
import org.laocat.handler.LaoCatAccessDeniedHandler;
import org.laocat.handler.LaoCatLogoutHandler;
import org.laocat.handler.LaoCatPermissionEvaluator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.server.session.DefaultWebSessionManager;

/**
 * @author LaoCat
 * @date 2022/6/15
 * @description 核心配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true, securedEnabled = true, prePostEnabled = true)
public class ValidateSecurityCoreConfig extends WebSecurityConfigurerAdapter {
    /**
     * 失败处理器
     */
    private final AuthenticationFailureHandler authenticationFailureHandler;
    /**
     * 成功处理器
     */
    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    /**
     * 无权限处理器
     */
    private final LaoCatAccessDeniedHandler accessDeniedHandler;
    /**
     * 登出处理器
     */
    private final LaoCatLogoutHandler logoutHandler;
    /**
     * 封装管理器
     */
    private final AuthorizeConfigManager authorizeConfigManager;

    private final UserServiceImpl userService;

    public ValidateSecurityCoreConfig(AuthenticationFailureHandler authenticationFailureHandler, AuthenticationSuccessHandler authenticationSuccessHandler, LaoCatAccessDeniedHandler accessDeniedHandler, LaoCatLogoutHandler logoutHandler, AuthorizeConfigManager authorizeConfigManager, UserServiceImpl userService) {
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.accessDeniedHandler = accessDeniedHandler;
        this.logoutHandler = logoutHandler;
        this.authorizeConfigManager = authorizeConfigManager;
        this.userService = userService;
    }


    /**
     * 注入身份管理器bean
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(
                new PasswordEncoder() {

                    @Override
                    public String encode(CharSequence rawPassword) {
                        return MD5Util.encode((String) rawPassword);
                    }

                    @Override
                    public boolean matches(CharSequence rawPassword, String encodedPassword) {
                        return encodedPassword.equals(MD5Util.encode((String) rawPassword));
                    }
                });
    }

    /**
     * @description: 注入自定义权限管理器
     * @author: LaoCat
     * @date: 2022/6/23
     * @returnType: org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler
     */
    @Bean
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
        DefaultWebSecurityExpressionHandler securityExpressionHandler = new DefaultWebSecurityExpressionHandler();
        securityExpressionHandler.setPermissionEvaluator(new LaoCatPermissionEvaluator());
        return securityExpressionHandler;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginProcessingUrl("/authentication")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .logout()
                .addLogoutHandler(logoutHandler)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .and()
                // 先加上这句话，否则登录的时候会出现403错误码，Could not verify the provided CSRF token because your session was not found.
                .csrf().disable();
        // 一定要放到最后，是因为config方法里最后做了其他任何方法都需要身份认证才能访问。
        // 放到前面的话，后面在加载.antMatchers(getPermitUrls()).permitAll()的时候也会被认为无权限，
        // 因为前面已经做了其他任何方法都需要身份认证才能访问，SpringSecurity是有先后顺序的。
        authorizeConfigManager.config(http);
    }
}
