package org.laocat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.laocat.entity.UserPermission;
import org.laocat.entity.req.PermissionReq;

import java.util.List;

/**
 * @description: @see
 * @author: LaoCat
 * @date: 2022/6/23
 */
public interface PermissionService extends IService<UserPermission> {
    /**
     * @description: 增加权限
     * @author: LaoCat
     * @date: 2022/6/23
     * @returnType: boolean
     */
    Boolean createPermission(UserPermission permission);

    /**
     * @description: 查询权限
     * @author: LaoCat
     * @date: 2022/6/23
     * @returnType: java.util.List<org.laocat.entity.UserPermission>
     */
    List<UserPermission> permissions(PermissionReq permissionReq);
}
