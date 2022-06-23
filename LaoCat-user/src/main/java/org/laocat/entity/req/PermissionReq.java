package org.laocat.entity.req;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @description: @see
 * @author: LaoCat
 * @date: 2022/6/23
 */
@Data
public class PermissionReq {

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 权限code
     */
    private String permissionCode;

    /**
     * 创建时间
     */
    private Date startTime;
    private Date endTime;
}
