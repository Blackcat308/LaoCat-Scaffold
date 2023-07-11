package org.laocat.security.serializer;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.laocat.auth.service.sms.LaoCatSmsAuthenticationToken;
import org.laocat.security.serializer.mixin.LaoCatSmsAuthenticationTokenMixin;
import org.springframework.security.jackson2.SecurityJackson2Modules;

/**
 * @description: @see
 * @author: LaoCat
 * @date: 2023/7/11
 */
@SuppressWarnings("serial")
public class LaoCatSmsAuthenticationTokenJackson2Module extends SimpleModule {

    public LaoCatSmsAuthenticationTokenJackson2Module() {
        super(LaoCatSmsAuthenticationTokenJackson2Module.class.getName(), new Version(1, 0, 0, null, null, null));
    }

    @Override
    public void setupModule(SetupContext context) {
        SecurityJackson2Modules.enableDefaultTyping(context.getOwner());
        context.setMixInAnnotations(LaoCatSmsAuthenticationToken.class, LaoCatSmsAuthenticationTokenMixin.class);
    }
}
