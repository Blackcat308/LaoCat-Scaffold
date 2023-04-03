package org.laocat.minio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @description: minio template 按需加载
 * @author: LaoCat
 * @date: 2023/4/3
 */
@EnableConfigurationProperties(LaoCatMinioConfig.class)
public class LaoCatMinioAutoConfigurer {
    private final LaoCatMinioConfig minioConfig;

    @Autowired
    public LaoCatMinioAutoConfigurer(LaoCatMinioConfig minioConfig) {
        this.minioConfig = minioConfig;
    }

    @Bean
    @ConditionalOnMissingBean(LaoCatMinio.class)
    @ConditionalOnProperty(name = "minio.url")
    public LaoCatMinio minio() {
        return new LaoCatMinio(minioConfig.getUrl(),minioConfig.getAccessKey(),minioConfig.getSecretKey());
    }
}
