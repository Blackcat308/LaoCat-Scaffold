package org.laocat.minio;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @description: minio配置
 * @author: LaoCat
 * @date: 2023/4/3
 */
@Configuration
@ConfigurationProperties(prefix = "minio")
public class LaoCatMinioConfig {

    /**
     * minio accessKey
     */
    private String accessKey;
    /**
     * minio secretKey
     */
    private String secretKey;
    /**
     * minio ip:port
     */
    private String url;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
