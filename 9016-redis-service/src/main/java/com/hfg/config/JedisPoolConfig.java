package com.hfg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

/**
 * @Author: Zero
 * @Date: 2022/4/20 21:00
 * @Description:
 */
@Configuration
public class JedisPoolConfig {

    @Bean
    public Jedis getJedis() {
        return new Jedis("localhost",6379);
    }
}
