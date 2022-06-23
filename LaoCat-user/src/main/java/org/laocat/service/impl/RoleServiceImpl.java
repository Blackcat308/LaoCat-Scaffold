package org.laocat.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.laocat.entity.UserRole;
import org.laocat.entity.req.UserRoleReq;
import org.laocat.mapper.RoleMapper;
import org.laocat.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author: LaoCat
 * @date: 2022/6/23
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, UserRole> implements RoleService {

    /**
     * @description: 新增角色
     * @author: LaoCat
     * @date: 2022/6/23
     * @returnType: java.lang.Boolean
     */
    @Override
    public Boolean createRole(UserRole userRole) {
        return this.save(userRole);
    }

    /**
     * @description: 修改角色
     * @author: LaoCat
     * @date: 2022/6/23
     * @returnType: java.lang.Boolean
     */
    @Override
    public Boolean updateRole(String roleId, String roleName) {
        UserRole userRole = Optional.ofNullable(this.getById(roleId)).orElseThrow(() -> new RuntimeException("角色信息不存在"));
        userRole.setRoleName(roleName);
        return this.updateById(userRole);
    }

    /**
     * @description: 查询角色列表
     * @author: LaoCat
     * @date: 2022/6/23
     * @returnType: java.util.List<org.laocat.entity.UserRole>
     */
    @Override
    public List<UserRole> roles(UserRoleReq userRoleReq) {
        return this.list(
                new LambdaQueryWrapper<UserRole>()
                        .eq(StrUtil.isNotBlank(userRoleReq.getRoleName()), UserRole::getRoleName, userRoleReq.getRoleName())
                        .eq(StrUtil.isNotBlank(userRoleReq.getRoleCode()), UserRole::getRoleCode, userRoleReq.getRoleCode())
                        .between(Objects.nonNull(userRoleReq.getStartTime()) && Objects.nonNull(userRoleReq.getEndTime()), UserRole::getGmtTime,
                                userRoleReq.getStartTime(), userRoleReq.getEndTime())
        );
    }
}
