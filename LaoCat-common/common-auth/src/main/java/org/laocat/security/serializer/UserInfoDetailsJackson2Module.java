package org.laocat.security.serializer;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.laocat.security.serializer.mixin.UserInfoDetailsMixin;
import org.laocat.auth.service.UserInfoDetails;
import org.springframework.security.jackson2.SecurityJackson2Modules;

/**
 * @description: @see
 * @author: LaoCat
 * @date: 2023/7/6
 */
@SuppressWarnings("serial")
public class UserInfoDetailsJackson2Module extends SimpleModule {

    public UserInfoDetailsJackson2Module() {
        super(UserInfoDetailsJackson2Module.class.getName(), new Version(1, 0, 0, null, null, null));
    }

    @Override
    public void setupModule(SetupContext context) {
        SecurityJackson2Modules.enableDefaultTyping(context.getOwner());
        context.setMixInAnnotations(UserInfoDetails.class, UserInfoDetailsMixin.class);
    }
}
