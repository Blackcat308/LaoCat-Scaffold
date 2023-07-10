package org.laocat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author LaoCat
 * @date 2022/6/14
 * @description 工作流引擎启动类
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableTransactionManagement
public class LaoCatFlowableApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(LaoCatFlowableApplication.class);
        springApplication.setAllowBeanDefinitionOverriding(true);
        springApplication.run(args);
    }

}
