package org.laocat.controller;

import lombok.AllArgsConstructor;
import org.laocat.core.response.structure.ResponseEntity;
import org.laocat.entity.UserRole;
import org.laocat.entity.req.UserRoleReq;
import org.laocat.service.RoleService;
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
public class RoleController {
    private final RoleService roleService;

    /**
     * @description: 新增角色
     * @author: LaoCat
     * @date: 2022/6/23
     * @returnType: org.laocat.core.response.structure.ResponseEntity<Boolean>
     */
    @PostMapping
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
    public ResponseEntity<List<UserRole>> roles(UserRoleReq userRoleReq) {
        return ResponseEntity.success(roleService.roles(userRoleReq));
    }


}
