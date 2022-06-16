package org.laocat.auth.manager;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * @author LaoCat
 * @date 2022/6/15
 */
public interface AuthorizeConfigProvider {

    void config(HttpSecurity httpSecurity) throws Exception;
}
