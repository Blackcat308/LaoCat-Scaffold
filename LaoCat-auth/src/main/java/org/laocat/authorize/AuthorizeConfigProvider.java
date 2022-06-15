package org.laocat.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * @author LaoCat
 * @date 2022/6/15
 * @description
 */
public interface AuthorizeConfigProvider {

    void config(HttpSecurity httpSecurity) throws Exception;
}
