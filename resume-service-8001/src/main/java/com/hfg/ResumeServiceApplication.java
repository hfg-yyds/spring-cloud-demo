package com.hfg;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: Zero
 * @Date: 2022/3/29 16:59
 * @Description:
 */
@SpringBootApplication
@Slf4j
@MapperScan("com.hfg.mapper")
@EnableDiscoveryClient
@EnableEurekaClient
@SpringCloudApplication
//@SpringBootApplication
//@EnableDiscoveryClient
//@EnableCircuitBreaker
public class ResumeServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResumeServiceApplication.class,args);
    }
}
