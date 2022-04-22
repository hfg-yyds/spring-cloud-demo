package com.hfg;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author: Zero
 * @Date: 2022/4/20 12:27
 * @Description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@Slf4j
public class RedisService {
    public static void main(String[] args) {
        SpringApplication.run(RedisService.class,args);
    }
}
