package com.sample.mybatis.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.sample.mybatis.mbg.mapper")
public class MyBatisConfig {
}
