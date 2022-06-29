package org.laocat.entity.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @description: @see
 * @author: LaoCat
 * @date: 2022/6/23
 */
@Data
@ApiModel(value="权限查询多条件请求类")
public class PermissionReq {

    /**
     * 权限名称
     */
    @ApiModelProperty(value = "权限名称")
    private String permissionName;

    /**
     * 权限code
     */
    @ApiModelProperty(value = "权限code")
    private String permissionCode;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "开始时间")
    private Date startTime;
    @ApiModelProperty(value = "结束时间")
    private Date endTime;
}
