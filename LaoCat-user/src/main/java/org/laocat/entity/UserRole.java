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
@ApiModel(value="角色表")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRole extends Model<UserRole>  implements Serializable {

	private static final long serialVersionUID =  4304069827705543330L;

	@TableId   
	private String id;

	/**
	 * 角色名称
	 */
	@ApiModelProperty(value = "角色名称")
	@NotBlank(message = "角色名称不能为null")
	private String roleName;

	/**
	 * 角色code
	 */
	@ApiModelProperty(value = "角色code")
	@NotBlank(message = "角色code不能为null")
	private String roleCode;

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
