package com.hfg;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @Author: Zero
 * @Date: 2022/3/31 12:45
 * @Description:
 */
@Slf4j
@SpringBootApplication
@EnableHystrixDashboard
@EnableEurekaClient
public class HystrixDashboard6002 {
    public static void main(String[] args) {
        log.info("HystrixDashboard6002开始启动了");
        SpringApplication.run(HystrixDashboard6002.class,args);
    }
}
