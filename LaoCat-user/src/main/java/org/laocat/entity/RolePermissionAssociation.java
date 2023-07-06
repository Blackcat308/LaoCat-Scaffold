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

import java.util.Date;

/**
 * @Description
 * @Author LaoCat
 * @Date 2022-06-23 03:06
 */

@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "角色 权限 关联表")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RolePermissionAssociation extends Model<RolePermissionAssociation> implements Serializable {

    private static final long serialVersionUID = 3608878553305835624L;

    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id")
    private String roleId;

    /**
     * 权限id
     */
    @ApiModelProperty(value = "权限id")
    private String permissionId;

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
