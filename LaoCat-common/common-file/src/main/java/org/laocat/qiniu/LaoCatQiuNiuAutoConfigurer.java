package org.laocat.qiniu;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
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
@EnableConfigurationProperties({LaoCatQiuNiuConfig.class})
@AllArgsConstructor
public class LaoCatQiuNiuAutoConfigurer {


    private final LaoCatQiuNiuConfig qiuNiuConfig;


    @Bean
    @ConditionalOnMissingBean(LaoCatQiuNiu.class)
    @ConditionalOnProperty(name = "qiniu.bucket")
    public LaoCatQiuNiu qiuNiu() {
        return new LaoCatQiuNiu(qiuNiuConfig.getBucket(), qiuNiuConfig.getAccessKey(), qiuNiuConfig.getSecretKey());
    }
}
