package org.laocat.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * @description: @see
 * @author: LaoCat
 * @date: 2022/6/29
 */
@Data
@ApiModel(value = "用户角色表")
public class UserRoleVO implements Serializable {

    private String id;

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

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date gmtTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date modifyTime;
}
