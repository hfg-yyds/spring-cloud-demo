package com.hfg;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @Author: Zero
 * @Date: 2022/3/31 12:01
 * @Description:
 */
@SpringBootApplication
@EnableEurekaClient
@EnableTurbine
@Slf4j
public class HystrixTurbine6001 {


    public static void main(String[] args) {
        log.info("HystrixTurbine6001开始启动了!!!");
        SpringApplication.run(HystrixTurbine6001.class,args);
    }
}
