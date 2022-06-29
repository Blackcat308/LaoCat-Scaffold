package org.laocat.entity.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @description: 角色请求类
 * @author: LaoCat
 * @date: 2022/6/23
 */
@Data
@ApiModel(value = "角色查询多条件请求类")
public class UserRoleReq {

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
     * 创建时间  开始 and 结束
     */
    @ApiModelProperty(value = "开始时间")
    private Date startTime;
    @ApiModelProperty(value = "结束时间")
    private Date endTime;

}
