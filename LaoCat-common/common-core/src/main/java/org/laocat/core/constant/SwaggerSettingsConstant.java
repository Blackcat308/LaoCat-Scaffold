package org.laocat.core.constant;

/**
 * @description: swagger配置常量
 * @author: LaoCat
 * @date: 2023/7/6
 */
public interface SwaggerSettingsConstant {

    /**
     * 基础包
     */
    String BASE_PACKAGE = "org.laocat.controller";

    /**
     * 各api名称
     */
    String API_FROM_USER = "LaoCat-user-API";
    String API_FROM_JOB = "LaoCat-job-API";
    String API_FROM_FILE = "LaoCat-file-API";
    String API_FROM_AUTH = "LaoCat-auth-API";
    String API_FROM_FLOWABLE = "LaoCat-flowable-API";
    /**
     * 详情
     */
    String DESCRIPTION = "<div style='font-size:14px;color:red;'>{}</div>";
    /**
     * 全局版本号
     */
    String GLOBAL_VERSION = "1.0.0";

    /**
     * @description: swagger 作者信息配置常量
     * @author: LaoCat
     * @date: 2023/7/6
     */
    interface ContactInfoConstant {
        String AUTHOR = "LaoCat";

        String GITHUB_URL = "https://github.com/Blackcat308";

        String EMAIL = "laocat308@outlook.com";
    }


}
