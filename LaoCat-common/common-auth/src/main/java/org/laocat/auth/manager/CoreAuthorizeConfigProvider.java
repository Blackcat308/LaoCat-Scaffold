package org.laocat.auth.manager;

import lombok.AllArgsConstructor;
import org.laocat.config.SecurityProperties;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

/**
 * @author LaoCat
 * @date 2022/6/15
 * @description 添加各种过滤权限的可以维护到yml里
 */
@Component
@Order(Byte.MIN_VALUE + 1)
@AllArgsConstructor
public class CoreAuthorizeConfigProvider implements AuthorizeConfigProvider {

    private final SecurityProperties securityProperties;

    @Override
    public void config(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers(getPermitUrls()).permitAll();
    }

    public String[] getPermitUrls() {
        return securityProperties.getIgnoreUrl().toArray(new String[0]);
    }
}
