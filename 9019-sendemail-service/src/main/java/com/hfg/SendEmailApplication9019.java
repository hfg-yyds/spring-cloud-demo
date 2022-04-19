package com.hfg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author: Zero
 * @Date: 2022/4/18 01:20
 * @Description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SendEmailApplication9019 {

    public static void main(String[] args) {
        SpringApplication.run(SendEmailApplication9019.class,args);
    }
}
