package com.demo.container;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;


/**
 * 项目搭建
 * 1.若启动无法暴露接口(404)时, 注意加上ComponentScan注解扫描controller包
 */
@EnableDiscoveryClient
@MapperScan("com.demo.dao")
@SpringBootApplication
@ComponentScan(basePackages = {"com.demo.service.**","com.demo.web.**"})
public class DemoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoServiceApplication.class, args);
    }
}

