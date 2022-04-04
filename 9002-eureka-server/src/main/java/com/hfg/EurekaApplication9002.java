package com.hfg;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author: Zero
 * @Date: 2022/3/29 18:23
 * @Description:
 */
@SpringBootApplication
@Slf4j
@EnableEurekaServer
public class EurekaApplication9002 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication9002.class,args);
    }
}
