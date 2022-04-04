package com.hfg.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Zero
 * @Date: 2022/3/31 16:11
 * @Description:
 */
@Configuration
public class FeignConfig {
    @Bean
    Logger.Level feignLevel() {
        return Logger.Level.FULL;
    }
}
