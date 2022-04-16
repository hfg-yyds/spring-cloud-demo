package com.hfg.config;

import lombok.SneakyThrows;
import org.apache.rocketmq.client.consumer.DefaultLitePullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Zero
 * @Date: 2022/4/4 13:48
 * @Description:
 */
@Configuration
public class ComsumerConfig {

    @SneakyThrows
    @Bean(name = "consumer")
    public DefaultLitePullConsumer getDefaultMQPushConsumer() {
        DefaultLitePullConsumer pullConsumer = new DefaultLitePullConsumer("consumerA");
        pullConsumer.setNamesrvAddr("192.168.88.128:9876");
        pullConsumer.subscribe("topic","*");
        return pullConsumer;
    }

}
