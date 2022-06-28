package org.laocat.service;

import org.laocat.core.response.structure.ResponseEntity;
import org.laocat.entity.UserRoleAssociation;
import org.laocat.entity.req.UserRoleAssociationReq;

import java.util.List;

/**
 * @description: @see
 * @author: LaoCat
 * @date: 2022/6/28
 */
public interface UserRoleAssociationService {

    /**
     * @description: 添加用户与角色关系
     * @author: LaoCat
     * @date: 2022/6/28
     * @returnType: int
     */
    int createUserRole(UserRoleAssociationReq userRoleAssociation);

    /**
     * @description: 查询某个用户的全部角色
     * @author: LaoCat
     * @date: 2022/6/28
     * @returnType: List<UserRoleAssociation>
     */
    List<UserRoleAssociation> selectUserRoleAssociationAllByUserId(String userId);


    /**
     * @description: 查询某个用户某个角色的详细信息
     * @author: LaoCat
     * @date: 2022/6/28
     * @returnType: List<UserRoleAssociation>
     */
    UserRoleAssociation selectUserRoleAssociationByUserId(String userId,String roleId);

    /**
     * @description: 解绑所有角色
     * @author: LaoCat
     * @date: 2022/6/28
     * @returnType: org.laocat.core.response.structure.ResponseEntity<?>
     */
    int deleteUserRoleAll(String userId);

    /**
     * @description: 解绑某一特定角色
     * @author: LaoCat
     * @date: 2022/6/28
     * @returnType: org.laocat.core.response.structure.ResponseEntity<?>
     */
    int deleteUserRole(String userId, String roleId);
}
