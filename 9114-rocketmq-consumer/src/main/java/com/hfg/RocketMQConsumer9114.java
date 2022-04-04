package com.hfg;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author: Zero
 * @Date: 2022/4/4 12:46
 * @Description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@Slf4j
public class RocketMQConsumer9114 {
    public static void main(String[] args) {
        log.info("RocketMQ消费者启动了！");
        SpringApplication.run(RocketMQConsumer9114.class,args);
    }
}
