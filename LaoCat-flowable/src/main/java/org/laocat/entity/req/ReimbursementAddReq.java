package org.laocat.entity.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @description: @see
 * @author: LaoCat
 * @date: 2023/7/8
 */
@Data
@ApiModel(value = "报销添加类")
public class ReimbursementAddReq {

    /**
     * 用户id
     */
    @NotBlank(message = "用户id不能为空")
    @ApiModelProperty("用户id")
    private String userId;
    /**
     * 金额
     */
    @NotNull(message = "金额不能为空")
    @ApiModelProperty("金额")
    private Integer money;
    /**
     * 描述
     */
    private String description;
}
