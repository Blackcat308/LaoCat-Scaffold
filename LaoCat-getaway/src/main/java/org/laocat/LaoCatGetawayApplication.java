package org.laocat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author LaoCat
 * @date 2022/6/14
 * @description 网关
 */
@SpringBootApplication
@EnableDiscoveryClient
public class LaoCatGetawayApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(LaoCatGetawayApplication.class);
        springApplication.setAllowBeanDefinitionOverriding(true);
        springApplication.run(args);
    }

}
