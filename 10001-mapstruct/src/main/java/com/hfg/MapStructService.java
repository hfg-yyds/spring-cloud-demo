package com.hfg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author: Zero
 * @Date: 2022/4/20 12:40
 * @Description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MapStructService {
    public static void main(String[] args) {
        SpringApplication.run(MapStructService.class,args);
    }
}
