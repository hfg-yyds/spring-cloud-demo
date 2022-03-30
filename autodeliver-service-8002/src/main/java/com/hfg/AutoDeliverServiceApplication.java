package com.hfg;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: Zero
 * @Date: 2022/3/29 17:08
 * @Description:
 */
@SpringBootApplication
@Slf4j
@MapperScan("com.hfg.mapper")
@EnableDiscoveryClient
public class AutoDeliverServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AutoDeliverServiceApplication.class,args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
