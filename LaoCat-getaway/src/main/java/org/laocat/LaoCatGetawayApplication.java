package org.laocat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author LaoCat
 * @date 2022/6/14
 * @description 网关
 */
@EnableSwagger2
@SpringBootApplication
@EnableDiscoveryClient
public class LaoCatGetawayApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(LaoCatGetawayApplication.class);
        springApplication.setAllowBeanDefinitionOverriding(true);
        springApplication.run(args);
    }

}
