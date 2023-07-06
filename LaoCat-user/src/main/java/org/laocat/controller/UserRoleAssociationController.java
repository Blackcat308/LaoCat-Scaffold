package org.laocat.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.laocat.core.response.structure.ResponseEntity;
import org.laocat.entity.UserRole;
import org.laocat.entity.req.UserRoleAssociationReq;
import org.laocat.service.UserRoleAssociationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 用户与角色中间表
 * @author: LaoCat
 * @date: 2022/6/28
 */
@RestController
@RequestMapping("userRole")
@AllArgsConstructor
@Api(tags = "用户与角色相关")
public class UserRoleAssociationController {
    private final UserRoleAssociationService userRoleAssociationService;

    /**
     * @description: 新建用户与角色关联关系
     * @author: LaoCat
     * @date: 2022/6/28
     * @returnType: org.laocat.core.response.structure.ResponseEntity<Boolean>
     */
    @PostMapping
    @ApiOperation(value = "新建用户与角色关联关系", notes = "新建用户与角色关联关系")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Boolean> createUserRole(@Valid @RequestBody UserRoleAssociationReq userRoleAssociation) {
        return userRoleAssociationService.createUserRole(userRoleAssociation) > 0 ? ResponseEntity.success() : ResponseEntity.fail();
    }

    /**
     * @description: 解绑所有
     * @author: LaoCat
     * @date: 2022/6/28
     * @returnType: org.laocat.core.response.structure.ResponseEntity<?>
     */
    @DeleteMapping("{userId}")
    @ApiOperation(value = "解绑所有", notes = "解绑所有")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteUserRoleAll(@PathVariable String userId) {
        return userRoleAssociationService.deleteUserRoleAll(userId) > 0 ? ResponseEntity.success() : ResponseEntity.fail();
    }

    /**
     * @description: 解绑某一角色
     * @author: LaoCat
     * @date: 2022/6/28
     * @returnType: org.laocat.core.response.structure.ResponseEntity<?>
     */
    @DeleteMapping("{userId}/{roleId}")
    @ApiOperation(value = "解绑某一角色", notes = "解绑某一角色")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteUserRole(@PathVariable String userId, @PathVariable String roleId) {
        return userRoleAssociationService.deleteUserRole(userId, roleId) > 0 ? ResponseEntity.success() : ResponseEntity.fail();
    }

    /**
     * @description: 根据userid获取所有角色 内部
     * @author: LaoCat
     * @date: 2022/6/28
     * @returnType: org.laocat.core.response.structure.ResponseEntity<?>
     */
    @GetMapping("/userRoleByUserId")
    @ApiOperation(value = "根据userid获取所有角色[Inner]", notes = "根据userid获取所有角色[Inner]")
    public List<UserRole> deleteUserRole(@RequestParam String userId) {
        return userRoleAssociationService.selectUserRolesByUserId(userId);
    }
}
