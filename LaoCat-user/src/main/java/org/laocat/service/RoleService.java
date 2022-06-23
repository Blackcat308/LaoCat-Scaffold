package org.laocat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.laocat.core.response.structure.ResponseEntity;
import org.laocat.entity.UserRole;
import org.laocat.entity.req.UserRoleReq;

import java.util.List;

/**
 * @author: LaoCat
 * @date: 2022/6/23
 */
public interface RoleService extends IService<UserRole> {
    /**
     * @description: 新增角色
     * @author: LaoCat
     * @date: 2022/6/23
     * @returnType: java.lang.Boolean
     */
    Boolean createRole(UserRole userRole);

    /**
     * @description: 修改角色昵称
     * @author: LaoCat
     * @date: 2022/6/23
     * @returnType: java.lang.Boolean
     */
    Boolean updateRole(String roleId, String roleName);

    /**
     * @description: 查询角色列表
     * @author: LaoCat
     * @date: 2022/6/23
     * @returnType: java.util.List<org.laocat.entity.UserRole>
     */
    List<UserRole> roles(UserRoleReq userRoleReq);
}
