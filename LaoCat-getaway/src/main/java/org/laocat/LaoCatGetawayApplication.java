package org.laocat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author LaoCat
 * @date 2022/6/14
 * @description 网关
 */
@SpringBootApplication
public class LaoCatGetawayApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(LaoCatGetawayApplication.class);
        springApplication.setAllowBeanDefinitionOverriding(true);
        springApplication.run(args);
    }

}
