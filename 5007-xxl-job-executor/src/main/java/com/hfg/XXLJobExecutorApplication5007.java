package com.hfg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author: Zero
 * @Date: 2022/4/19 19:42
 * @Description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class XXLJobExecutorApplication5007 {
    public static void main(String[] args) {
        SpringApplication.run(XXLJobExecutorApplication5007.class,args);
    }
}
