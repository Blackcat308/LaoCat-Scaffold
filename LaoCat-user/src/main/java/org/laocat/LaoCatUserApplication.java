package org.laocat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author LaoCat
 * @date 2022/6/14
 * @description 用户中心
 */

@EnableSwagger2
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
