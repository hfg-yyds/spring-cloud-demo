package com.hfg;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @Author: Zero
 * @Date: 2022/4/4 12:46
 * @Description:
 */
@SpringBootApplication
@Slf4j
@MapperScan("com.hfg.mapper")
public class RocketMQConsumer9114 {
    public static void main(String[] args) {
        log.info("RocketMQ消费者启动了！");
        SpringApplication.run(RocketMQConsumer9114.class,args);
    }
}
