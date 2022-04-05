package com.hfg;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: Zero
 * @Date: 2022/4/5 21:24
 * @Description:
 */
@SpringBootApplication
@Slf4j
@EnableEurekaClient
public class AuthorizationService9004 {
    public static void main(String[] args) {
        log.info("认证服务启动了,负责颁发token");
        SpringApplication.run(AuthorizationService9004.class,args);
    }
}
