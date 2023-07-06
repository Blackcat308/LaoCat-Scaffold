package org.laocat.handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.log.StaticLog;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @description: 自定义权限管策略
 * @author: LaoCat
 * @date: 2022/6/23
 */
@Component
public class LaoCatPermissionEvaluator implements PermissionEvaluator {


    @Override
    public boolean hasPermission(Authentication authentication, Object customizeRole, Object customizePermission) {
        User user = (User) authentication.getPrincipal();

        Collection<GrantedAuthority> authorities = user.getAuthorities();
        List<GrantedAuthority> grantedAuthorities = authorities
                .stream()
                .filter(a -> StringUtils.equals(a.getAuthority(), customizeRole.toString())).collect(Collectors.toList());

        for (GrantedAuthority authority : grantedAuthorities) {
            String[] pers = {"admin", "custom"};
            List<String> permissions = Arrays.asList(pers);
            // 遍历是否有存在的权限
            for (String permission : permissions) {
                if (permissions.contains(customizePermission.toString())) {
                    return true;
                }
            }

        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}
