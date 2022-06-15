package org.laocat.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author LaoCat
 * @date 2022/6/15
 * @description TODO
 */
@MapperScan("org.laocat.mapper.**")
@Configuration
public class MybatisPlusConfig {
}
