package com.sample.swagger.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.sample.swagger.mbg.mapper")
public class MyBatisConfig {
}
