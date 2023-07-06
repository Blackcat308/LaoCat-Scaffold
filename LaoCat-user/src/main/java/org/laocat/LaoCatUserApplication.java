package org.laocat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author LaoCat
 * @date 2022/6/14
 * @description 用户中心
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableTransactionManagement
public class LaoCatUserApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(LaoCatUserApplication.class);
        springApplication.setAllowBeanDefinitionOverriding(true);
        springApplication.run(args);
    }

}
