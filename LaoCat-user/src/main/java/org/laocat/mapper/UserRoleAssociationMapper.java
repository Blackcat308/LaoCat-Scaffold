package org.laocat.mapper;

import org.apache.ibatis.annotations.Param;
import org.laocat.entity.UserRole;
import org.laocat.entity.UserRoleAssociation;
import org.laocat.entity.req.UserRoleAssociationReq;

import java.util.List;

/**
 * @description: @see
 * @author: LaoCat
 * @date: 2022/6/28
 */
public interface UserRoleAssociationMapper {

    /**
     * @description: 添加用户与角色关系
     * @author: LaoCat
     * @date: 2022/6/28
     * @returnType: void
     */
    int createUserRole(@Param("userRoleAssociation") UserRoleAssociationReq userRoleAssociation);

    /**
     * @description: 查询用户全部角色
     * @author: LaoCat
     * @date: 2022/6/28
     * @returnType: java.util.List<org.laocat.entity.UserRoleAssociation>
     */
    List<UserRoleAssociation> selectUserRoleAssociationAllByUserId(@Param("userId") String userId);

    /**
     * @description: 查询用户某一角色的详细信息
     * @author: LaoCat
     * @date: 2022/6/28
     * @returnType: org.laocat.entity.UserRoleAssociation
     */
    UserRoleAssociation selectUserRoleAssociationByUserId(@Param("userId") String userId, @Param("roleId") String roleId);

    /**
     * @description: 解绑用户所有角色
     * @author: LaoCat
     * @date: 2022/6/28
     * @returnType: org.laocat.entity.UserRoleAssociation
     */
    int deleteUserRoleAll(@Param("userId") String userId);

    /**
     * @description: 解绑用户某一角色
     * @author: LaoCat
     * @date: 2022/6/28
     * @returnType: org.laocat.entity.UserRoleAssociation
     */
    int deleteUserRole(@Param("userId") String userId, @Param("roleId") String roleId);

    /**
     * @description: 查询用户角色
     * @author: LaoCat
     * @date: 2022/6/29
     * @returnType: java.util.List<org.laocat.entity.UserRole>
     */
    List<UserRole> selectUserRolesByUserId(@Param("userId") String userId);
}
