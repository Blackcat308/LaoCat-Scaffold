package org.laocat.entity.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "用户与角色创建请求类")
public class UserRoleAssociationReq {

    @NotBlank(message = "用户不能为空")
    @ApiModelProperty(value = "用户id")
    private String userId;

    @NotEmpty(message = "角色至少选择一个")
    @ApiModelProperty(value = "角色id集")
    private List<String> roleIds;
}
