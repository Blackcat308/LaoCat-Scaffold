package org.laocat.constant;

/**
 * @author LaoCat
 * @date 2022/6/15
 * @description 安全模块常量
 */
public interface AuthConstant {

    /**
     * 随机字符串
     */
    String BASIC_STR = "abcdefghijklmnopqrstuvwxyz0123456789";

    /**
     * aes加密方式
     */
    String AES = "AES";

    /**
     * 随机串长度
     */
    int RANDOM_STR_LENGTH = 6;

    /**
     * 请求体类型
     */
    String CONTENT_TYPE = "application/json;charset=UTF-8";

    /**
     * 授权请求头
     */
    String AUTHORIZATION = "Authorization";

    /**
     * BEARER
     */
    String BEARER = "Bearer ";

    /**
     * randomKey
     */
    String RANDOM_KEY = "randomKey";

    /**
     * 盐
     */
    String SALT = "laocat";

    /**
     * 退出路径
     */
    String LOGOUT_URL = "/logout";

    /**
     * 请求密码key
     */
    String REQ_PARAM_PASSWORD_KEY = "password";

    /**
     * 角色默认配置头
     */
    String DEFAULT_ROLE_PREFIX = "ROLE_";


}
