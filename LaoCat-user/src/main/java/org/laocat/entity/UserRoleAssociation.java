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
 * @Author  LaoCat 
 * @Date 2022-06-23 03:06 
 */

@EqualsAndHashCode(callSuper = true)
@ApiModel(value="用户 角色 关联表")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleAssociation extends Model<UserRoleAssociation>  implements Serializable {

	private static final long serialVersionUID =  5499441384782974000L;

	/**
	 * 用户id
	 */
	@ApiModelProperty(value = "用户id")
	@NotBlank(message = "用户不能为空")
	private String userId;

	/**
	 * 角色id
	 */
	@ApiModelProperty(value = "角色id")
	@NotBlank(message = "角色不能为空")
	private String roleId;

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
