package org.laocat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description: 文件服务启动类
 * @author: LaoCat
 * @date: 2023/4/4
 */
@EnableDiscoveryClient
@SpringBootApplication
public class LaoCatFileApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(LaoCatFileApplication.class);
        springApplication.setAllowBeanDefinitionOverriding(true);
        springApplication.run(args);
    }
}
