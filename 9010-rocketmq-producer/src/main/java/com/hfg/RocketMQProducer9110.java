package com.hfg;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author: Zero
 * @Date: 2022/4/4 09:01
 * @Description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@Slf4j
public class RocketMQProducer9110 {
    public static void main(String[] args) {
        log.info("RocketMQ服务4001启动了");
        SpringApplication.run(RocketMQProducer9110.class,args);
    }
}
