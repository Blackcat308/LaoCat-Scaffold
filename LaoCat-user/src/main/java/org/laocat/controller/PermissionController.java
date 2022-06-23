package org.laocat.controller;

import lombok.AllArgsConstructor;
import org.laocat.core.response.structure.ResponseEntity;
import org.laocat.entity.UserPermission;
import org.laocat.entity.req.PermissionReq;
import org.laocat.service.PermissionService;
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
public class PermissionController {
    private final PermissionService permissionService;

    /**
     * @description: 增加权限
     * @author: LaoCat
     * @date: 2022/6/23
     * @returnType: org.laocat.core.response.structure.ResponseEntity<?>
     */
    @PostMapping
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
    public ResponseEntity<List<UserPermission>> permissions(PermissionReq permissionReq) {
        return ResponseEntity.success(permissionService.permissions(permissionReq));
    }

}
