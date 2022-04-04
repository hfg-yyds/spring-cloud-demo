package com.hfg;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: Zero
 * @Date: 2022/4/4 09:01
 * @Description:
 */
@SpringBootApplication
@Slf4j
public class RocketMQService9110 {
    public static void main(String[] args) {
        log.info("RocketMQ服务4001启动了");
        SpringApplication.run(RocketMQService9110.class,args);
    }
}
