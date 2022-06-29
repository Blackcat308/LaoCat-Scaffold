package org.laocat.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.laocat.entity.UserRole;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @description: @see
 * @author: LaoCat
 * @date: 2022/6/29
 */
@Data
@ApiModel(value = "用户详细信息实体")
public class UserInfoDetailVO implements Serializable {

    @ApiModelProperty(value = "用户id")
    private String id;

    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date gmtTime;

    @ApiModelProperty(value = "角色集合")
    private List<UserRoleDetailVO> userRoleDetails;
}
