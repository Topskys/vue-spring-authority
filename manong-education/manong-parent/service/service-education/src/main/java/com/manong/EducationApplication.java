package com.manong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//加载Mapper接口
@MapperScan("com.manong.dao")
@SpringBootApplication
@EnableDiscoveryClient //开启Nacos客户端注解支持
@EnableFeignClients //开启OpenFeign注解支持
public class EducationApplication {
    public static void main(String[] args) {
        SpringApplication.run(EducationApplication.class,args);
    }
}
