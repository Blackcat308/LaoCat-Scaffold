package org.laocat.qiniu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @description: 七牛云
 * @author: LaoCat
 * @date: 2023/4/3
 */
@EnableConfigurationProperties(LaoCatQiuNiuConfig.class)
public class LaoCatQiuNiuAutoConfigurer {
    private final LaoCatQiuNiuConfig qiuNiuConfig;

    @Autowired
    public LaoCatQiuNiuAutoConfigurer(LaoCatQiuNiuConfig qiuNiuConfig) {
        this.qiuNiuConfig = qiuNiuConfig;
    }

    @Bean
    @ConditionalOnMissingBean(LaoCatQiuNiu.class)
    @ConditionalOnProperty(name = "qiniu.bucket")
    public LaoCatQiuNiu qiuNiu() {
        return new LaoCatQiuNiu(qiuNiuConfig.getBucket(), qiuNiuConfig.getAccessKey(), qiuNiuConfig.getSecretKey());
    }
}
