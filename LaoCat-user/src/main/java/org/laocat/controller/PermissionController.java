package org.laocat.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.laocat.core.response.structure.ResponseEntity;
import org.laocat.entity.UserPermission;
import org.laocat.entity.req.PermissionReq;
import org.laocat.service.PermissionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 权限控制层
 * @author: LaoCat
 * @date: 2022/6/23
 */
@RestController
@RequestMapping("permission")
@AllArgsConstructor
@Api(tags = "权限相关")
public class PermissionController {
    private final PermissionService permissionService;

    /**
     * @description: 增加权限
     * @author: LaoCat
     * @date: 2022/6/23
     * @returnType: org.laocat.core.response.structure.ResponseEntity<?>
     */
    @PostMapping
    @ApiOperation(value = "增加权限", notes = "增加权限")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> createPermission(@Valid @RequestBody UserPermission permission) {
        return permissionService.createPermission(permission) ? ResponseEntity.success() : ResponseEntity.fail();
    }

    /**
     * @description: 删除权限 @TODO 需要解绑
     * @author: LaoCat
     * @date: 2022/6/23
     * @returnType: org.laocat.core.response.structure.ResponseEntity<?>
     */
    @DeleteMapping
    @ApiOperation(value = "删除权限", notes = "删除权限")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deletePermission() {
        return ResponseEntity.success();
    }


    /**
     * @description: 查看权限列表
     * @author: LaoCat
     * @date: 2022/6/23
     * @returnType: org.laocat.core.response.structure.ResponseEntity<List < UserPermission>>
     */
    @GetMapping
    @ApiOperation(value = "查看权限列表", notes = "查看权限列表")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserPermission>> permissions(PermissionReq permissionReq) {
        return ResponseEntity.success(permissionService.permissions(permissionReq));
    }

}
