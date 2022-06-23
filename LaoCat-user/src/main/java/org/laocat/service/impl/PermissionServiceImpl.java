package org.laocat.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.laocat.entity.UserPermission;
import org.laocat.entity.req.PermissionReq;
import org.laocat.mapper.PermissionMapper;
import org.laocat.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author: LaoCat
 * @date: 2022/6/23
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, UserPermission> implements PermissionService {

    /**
     * @description: 增加权限
     * @author: LaoCat
     * @date: 2022/6/23
     * @returnType: boolean
     */
    @Override
    public Boolean createPermission(UserPermission permission) {
        return this.save(permission);
    }

    /**
     * @description: 查询权限
     * @author: LaoCat
     * @date: 2022/6/23
     * @returnType: java.util.List<org.laocat.entity.UserPermission>
     */
    @Override
    public List<UserPermission> permissions(PermissionReq permissionReq) {
        return this.list(
                new LambdaQueryWrapper<UserPermission>()
                        .eq(StrUtil.isNotBlank(permissionReq.getPermissionName()), UserPermission::getPermissionName, permissionReq.getPermissionName())
                        .eq(StrUtil.isNotBlank(permissionReq.getPermissionCode()), UserPermission::getPermissionCode, permissionReq.getPermissionCode())
                        .between(Objects.nonNull(permissionReq.getStartTime()) && Objects.nonNull(permissionReq.getEndTime()), UserPermission::getGmtTime,
                                permissionReq.getStartTime(), permissionReq.getEndTime())
        );
    }

}
