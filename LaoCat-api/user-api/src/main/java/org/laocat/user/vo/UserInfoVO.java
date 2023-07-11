package org.laocat.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description 用户vo
 * @Author LaoCat
 * @Date 2022-06-15 04:43
 */
@Data
@ApiModel(value = "用户信息表")
public class UserInfoVO implements Serializable {

    private static final long serialVersionUID = 97097766963848492L;

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
     * 手机号
     */
    @ApiModelProperty(value = "密码")
    private String phone;

}
