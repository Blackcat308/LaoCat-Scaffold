package org.laocat.entity.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @description: @see
 * @author: LaoCat
 * @date: 2022/6/28
 */
@Data
public class UserRoleAssociationReq {

    @NotBlank(message = "用户不能为空")
    private String userId;

    @NotEmpty(message = "角色至少选择一个")
    private List<String> roleIds;
}
