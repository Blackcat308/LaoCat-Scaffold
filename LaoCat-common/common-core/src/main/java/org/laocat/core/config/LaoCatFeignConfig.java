package org.laocat.core.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * @Description 开启feign并 注入spring上下文中
 * @Author LaoCat
 * @Date 2022-06-15 19:30
 */
@Configuration
@EnableFeignClients({"org.laocat.**"})
public class LaoCatFeignConfig {
}
