package org.laocat.controller;

import lombok.AllArgsConstructor;
import org.laocat.core.response.structure.ResponseEntity;
import org.laocat.entity.UserRoleAssociation;
import org.laocat.entity.req.UserRoleAssociationReq;
import org.laocat.service.UserRoleAssociationService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @description: 用户与角色中间表
 * @author: LaoCat
 * @date: 2022/6/28
 */
@RestController
@RequestMapping("userRole")
@AllArgsConstructor
public class UserRoleAssociationController {
    private final UserRoleAssociationService userRoleAssociationService;

    /**
     * @description: 新建用户与角色关联关系
     * @author: LaoCat
     * @date: 2022/6/28
     * @returnType: org.laocat.core.response.structure.ResponseEntity<Boolean>
     */
    @PostMapping
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
    public ResponseEntity<?> deleteUserRole(@PathVariable String userId,@PathVariable String roleId) {
        return userRoleAssociationService.deleteUserRole(userId,roleId) > 0 ? ResponseEntity.success() : ResponseEntity.fail();
    }
}
