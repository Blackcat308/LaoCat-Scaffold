package org.laocat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author LaoCat
 * @date 2022/6/14
 * @description 授权中心
 */
@EnableDiscoveryClient
@SpringBootApplication
public class LaoCatAuthApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(LaoCatAuthApplication.class);
        springApplication.setAllowBeanDefinitionOverriding(true);
        springApplication.run(args);
    }

}
