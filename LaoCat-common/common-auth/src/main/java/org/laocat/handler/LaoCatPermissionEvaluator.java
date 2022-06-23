package org.laocat.handler;

import cn.hutool.log.StaticLog;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @description: 自定义权限管策略
 * @author: LaoCat
 * @date: 2022/6/23
 */
@Component
public class LaoCatPermissionEvaluator implements PermissionEvaluator {


    @Override
    public boolean hasPermission(Authentication authentication, Object o, Object o1) {
        StaticLog.info("authentication {}", authentication);
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}
