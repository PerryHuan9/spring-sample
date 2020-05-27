package com.sample.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.sample.mbg.mapper")
public class MyBatisConfig {
}
