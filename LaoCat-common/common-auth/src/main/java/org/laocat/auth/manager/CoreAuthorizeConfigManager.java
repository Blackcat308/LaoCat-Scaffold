package org.laocat.auth.manager;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author LaoCat
 * @date 2022/6/15
 */
@Component
public class CoreAuthorizeConfigManager implements AuthorizeConfigManager {

    private final List<AuthorizeConfigProvider> authorizeConfigProviders;

    public CoreAuthorizeConfigManager(List<AuthorizeConfigProvider> authorizeConfigProviders) {
        this.authorizeConfigProviders = authorizeConfigProviders;
    }

    @Override
    public void config(HttpSecurity httpSecurity) throws Exception {
        for (AuthorizeConfigProvider provider : authorizeConfigProviders) {
            provider.config(httpSecurity);
        }
    }
}
