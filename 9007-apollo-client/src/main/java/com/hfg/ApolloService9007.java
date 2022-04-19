package com.hfg;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author: Zero
 * @Date: 2022/4/1 17:06
 * @Description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@Slf4j
public class ApolloService9007 {

    public static void main(String[] args) {
        log.info("阿波罗服务开始启动了");
        SpringApplication.run(ApolloService9007.class,args);
    }

}
