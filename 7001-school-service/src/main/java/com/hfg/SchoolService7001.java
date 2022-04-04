package com.hfg;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: Zero
 * @Date: 2022/3/29 09:46
 * @Description:
 */

@Slf4j
@SpringBootApplication
@MapperScan("com.hfg.mapper")
public class SchoolService7001 {
    public static void main(String[] args) {
        log.info("服务开始启动");
        SpringApplication.run(SchoolService7001.class,args);
    }
}