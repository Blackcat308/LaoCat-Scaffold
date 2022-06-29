package org.laocat.service.impl;

import cn.hutool.core.collection.CollUtil;
import lombok.AllArgsConstructor;
import org.laocat.entity.UserRole;
import org.laocat.entity.UserRoleAssociation;
import org.laocat.entity.req.UserRoleAssociationReq;
import org.laocat.mapper.UserRoleAssociationMapper;
import org.laocat.service.UserRoleAssociationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: @see
 * @author: LaoCat
 * @date: 2022/6/28
 */
@Service
@AllArgsConstructor
public class UserRoleAssociationServiceImpl implements UserRoleAssociationService {
    private final UserRoleAssociationMapper userRoleAssociationMapper;

    /**
     * @description: 添加用户与角色关系
     * @author: LaoCat
     * @date: 2022/6/28
     * @returnType: void
     */
    @Override
    public int createUserRole(UserRoleAssociationReq userRoleAssociation) {
        List<String> roleIdsBySelect = null;
        // 获取已经绑定的角色id
        List<UserRoleAssociation> userRoleAssociations = this.selectUserRoleAssociationAllByUserId(userRoleAssociation.getUserId());
        if (CollUtil.isNotEmpty(userRoleAssociations)) {
            roleIdsBySelect = userRoleAssociations.stream().map(UserRoleAssociation::getRoleId).collect(Collectors.toList());
        }
        // 利用huTool的 求差集 得到未绑定的角色id
        List<String> notBindRoleIds = CollUtil.subtractToList(userRoleAssociation.getRoleIds(), roleIdsBySelect);
        userRoleAssociation.setRoleIds(notBindRoleIds);
        return userRoleAssociationMapper.createUserRole(userRoleAssociation);
    }

    /**
     * @description: 查询用户全部角色
     * @author: LaoCat
     * @date: 2022/6/28
     * @returnType: java.util.List<org.laocat.entity.UserRoleAssociation>
     */
    @Override
    public List<UserRoleAssociation> selectUserRoleAssociationAllByUserId(String userId) {
        return userRoleAssociationMapper.selectUserRoleAssociationAllByUserId(userId);
    }

    /**
     * @description: 查询用户某一角色的详细信息
     * @author: LaoCat
     * @date: 2022/6/28
     * @returnType: org.laocat.entity.UserRoleAssociation
     */
    @Override
    public UserRoleAssociation selectUserRoleAssociationByUserId(String userId, String roleId) {
        return userRoleAssociationMapper.selectUserRoleAssociationByUserId(userId, roleId);
    }

    /**
     * @description: 解绑用户所有角色
     * @author: LaoCat
     * @date: 2022/6/28
     * @returnType: int
     */
    @Override
    public int deleteUserRoleAll(String userId) {
        return userRoleAssociationMapper.deleteUserRoleAll(userId);
    }

    /**
     * @description: 解绑某一角色
     * @author: LaoCat
     * @date: 2022/6/28
     * @returnType: int
     */
    @Override
    public int deleteUserRole(String userId, String roleId) {
        return userRoleAssociationMapper.deleteUserRole(userId, roleId);
    }

    /**
     * @description: 查询用户角色
     * @author: LaoCat
     * @date: 2022/6/29
     * @returnType: java.util.List<org.laocat.entity.UserRole>
     */
    @Override
    public List<UserRole> selectUserRolesByUserId(String userId) {
        return userRoleAssociationMapper.selectUserRolesByUserId(userId);
    }
}
