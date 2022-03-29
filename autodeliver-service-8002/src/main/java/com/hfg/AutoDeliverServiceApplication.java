package com.hfg;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: Zero
 * @Date: 2022/3/29 17:08
 * @Description:
 */
@SpringBootApplication
@Slf4j
@MapperScan("com.hfg.mapper")
public class AutoDeliverServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AutoDeliverServiceApplication.class,args);
    }
}
