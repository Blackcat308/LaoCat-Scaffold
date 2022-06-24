package org.laocat.permission;

import cn.hutool.log.StaticLog;
import org.springframework.stereotype.Component;

/**
 * @description: @see
 * @author: LaoCat
 * @date: 2022/6/24
 */
interface LaoCatPermission {
    /**
     * @description: 检测是否存在该权限
     * @author: LaoCat
     * @date: 2022/6/24
     * @returnType: boolean
     */
    boolean check(String permission);
}


/**
 * @description: 自定义检测
 * @author: LaoCat
 * @date: 2022/6/24
 */
@Component("LaoCatPermission")
public class LaoCatPermissionAchieve implements LaoCatPermission {
    /**
     * @description: 检查自定义权限是否存在
     * @author: LaoCat
     * @date: 2022/6/24
     * @returnType: boolean
     */
    @Override
    public boolean check(String permission) {
        StaticLog.info("进入自定义匹配器...");
        return true;
    }
}
