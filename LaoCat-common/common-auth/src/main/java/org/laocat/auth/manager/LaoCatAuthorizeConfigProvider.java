package org.laocat.auth.manager;

import org.laocat.filters.JwtAuthenticationTokenFilter;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author LaoCat
 * @date 2022/6/15
 * @description
 */
@Component
@Order(Byte.MIN_VALUE + 2)
public class LaoCatAuthorizeConfigProvider implements AuthorizeConfigProvider {

    private final JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    public LaoCatAuthorizeConfigProvider(JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter) {
        this.jwtAuthenticationTokenFilter = jwtAuthenticationTokenFilter;
    }

    @Override
    public void config(HttpSecurity httpSecurity) throws Exception {
        /*
         * 基于token，所以不需要session
         * 然后来吧我们的jwt过滤器加到 登陆登陆验证器的前面
         */
        httpSecurity
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
