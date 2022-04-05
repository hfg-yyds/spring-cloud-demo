package com.hfg.config;

import lombok.SneakyThrows;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Zero
 * @Date: 2022/4/4 13:13
 * @Description:
 */
@Configuration
public class ProducerConfig {
//    @SneakyThrows
//    @Bean(name = "producer")
//    public DefaultMQProducer getMqProducer() {
//        DefaultMQProducer mqProducer = new DefaultMQProducer("producer");
//        mqProducer.setNamesrvAddr("192.168.88.128:9876");
//        mqProducer.start();
//        return mqProducer;
//    }

}
