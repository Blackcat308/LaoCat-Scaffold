package org.laocat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description: 任务调度启动类
 * @author: LaoCat
 * @date: 2023/4/3
 */
@EnableSwagger2
@EnableDiscoveryClient
@SpringBootApplication
public class LaoCatJobApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(LaoCatJobApplication.class);
        springApplication.setAllowBeanDefinitionOverriding(true);
        springApplication.run(args);
    }
}
