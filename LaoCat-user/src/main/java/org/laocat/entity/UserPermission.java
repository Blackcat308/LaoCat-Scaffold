package org.laocat.entity;

import java.io.Serializable;

import lombok.Data;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @Description
 * @Author LaoCat
 * @Date 2022-06-23 03:06
 */

@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "权限表")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPermission extends Model<UserPermission> implements Serializable {

    private static final long serialVersionUID = 3952458751479006484L;

    @TableId
    private String id;

    /**
     * 权限名称
     */
    @ApiModelProperty(value = "权限名称")
    @NotBlank(message = "权限名称不能为null")
    private String permissionName;

    /**
     * 权限code
     */
    @ApiModelProperty(value = "权限code")
    @NotBlank(message = "权限code不能为null")
    private String permissionCode;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.UPDATE)
    private Date modifyTime;

}
