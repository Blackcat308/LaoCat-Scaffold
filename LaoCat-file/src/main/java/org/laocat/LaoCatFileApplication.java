package org.laocat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description: 文件服务启动类
 * @author: LaoCat
 * @date: 2023/4/4
 */
@EnableSwagger2
@EnableDiscoveryClient
@SpringBootApplication
public class LaoCatFileApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(LaoCatFileApplication.class);
        springApplication.setAllowBeanDefinitionOverriding(true);
        springApplication.run(args);
    }
}
