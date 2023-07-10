package org.laocat.config;

import org.flowable.spring.SpringProcessEngineConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.springframework.context.annotation.Configuration;

/**
 * @description: flowable配置类
 * @author: LaoCat
 * @date: 2023/7/7
 */
@Configuration
public class FlowableConfiguration implements EngineConfigurationConfigurer<SpringProcessEngineConfiguration> {

    private final static String FONT = "宋体";

    /**
     * @description:  防止中文乱码
     * @author: LaoCat
     * @date: 2023/7/7
     * @returnType: void
     */
    @Override
    public void configure(SpringProcessEngineConfiguration processEngineConfiguration) {
        processEngineConfiguration.setActivityFontName(FONT);
        processEngineConfiguration.setLabelFontName(FONT);
        processEngineConfiguration.setAnnotationFontName(FONT);
    }
}
