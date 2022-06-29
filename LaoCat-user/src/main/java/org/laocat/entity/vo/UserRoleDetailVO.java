package org.laocat.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @description: @see
 * @author: LaoCat
 * @date: 2022/6/29
 */
@Data
@ApiModel(value = "角色详细信息实体")
public class UserRoleDetailVO implements Serializable {
    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String roleName;

    /**
     * 角色code
     */
    @ApiModelProperty(value = "角色code")
    private String roleCode;
}
