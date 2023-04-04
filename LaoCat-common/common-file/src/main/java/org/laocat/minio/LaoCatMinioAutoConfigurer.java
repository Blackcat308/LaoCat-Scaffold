package org.laocat.minio;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @description: minio template 按需加载
 * @author: LaoCat
 * @date: 2023/4/3
 */
@EnableConfigurationProperties({LaoCatMinioConfig.class})
@AllArgsConstructor
public class LaoCatMinioAutoConfigurer {

    private final LaoCatMinioConfig minioConfig;

    @Bean
    @ConditionalOnMissingBean(LaoCatMinio.class)
    @ConditionalOnProperty(name = "minio.url")
    public LaoCatMinio minio() {
        return new LaoCatMinio(minioConfig.getUrl(), minioConfig.getAccessKey(), minioConfig.getSecretKey());
    }
}
