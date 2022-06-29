package org.laocat.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.laocat.core.response.structure.ResponseEntity;
import org.laocat.entity.UserRole;
import org.laocat.entity.req.UserRoleReq;
import org.laocat.service.RoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 角色控制层
 * @author: LaoCat
 * @date: 2022/6/23
 */
@RestController
@RequestMapping("role")
@AllArgsConstructor
@Api(tags = "角色相关")
public class RoleController {
    private final RoleService roleService;

    /**
     * @description: 新增角色
     * @author: LaoCat
     * @date: 2022/6/23
     * @returnType: org.laocat.core.response.structure.ResponseEntity<Boolean>
     */
    @PostMapping
    @ApiOperation(value = "新增角色",notes = "新增角色")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Boolean> createRole(@Valid @RequestBody UserRole userRole) {
        return roleService.createRole(userRole) ? ResponseEntity.success() : ResponseEntity.fail();
    }

    /**
     * @description: 删除角色 @TODO 需解绑关联关系再进行删除
     * @author: LaoCat
     * @date: 2022/6/23
     * @returnType: org.laocat.core.response.structure.ResponseEntity<?>
     */
    @DeleteMapping
    @ApiOperation(value = "删除角色",notes = "删除角色")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteRole(@Valid @RequestBody UserRole userRole) {
        return ResponseEntity.success();
    }

    /**
     * @description: 修改角色名称   code不可被修改 且全局唯一
     * @author: LaoCat
     * @date: 2022/6/23
     * @returnType: org.laocat.core.response.structure.ResponseEntity<?>
     */
    @PutMapping("/{roleId}/{roleName}")
    @ApiOperation(value = "修改角色名称[code不可被修改且全局唯一]",notes = "修改角色名称[code不可被修改且全局唯一]")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateRole(@PathVariable String roleId, @PathVariable String roleName) {
        return roleService.updateRole(roleId, roleName) ? ResponseEntity.success() : ResponseEntity.fail();
    }

    /**
     * @description: 查询角色列表
     * @author: LaoCat
     * @date: 2022/6/23
     * @returnType: org.laocat.core.response.structure.ResponseEntity<List<UserRole>>
     */
    @GetMapping
    @ApiOperation(value = "查询角色列表",notes = "查询角色列表")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserRole>> roles(UserRoleReq userRoleReq) {
        return ResponseEntity.success(roleService.roles(userRoleReq));
    }


}
