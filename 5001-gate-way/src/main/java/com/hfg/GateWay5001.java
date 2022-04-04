package com.hfg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: Zero
 * @Date: 2022/3/31 16:41
 * @Description:
 */
@SpringBootApplication
@EnableEurekaClient
public class GateWay5001 {
    public static void main(String[] args) {
        SpringApplication.run(GateWay5001.class,args);
    }
}
