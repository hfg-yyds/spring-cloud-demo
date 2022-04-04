package com.hfg.config;

import ch.qos.logback.core.BasicStatusManager;
import lombok.SneakyThrows;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.MessageExt;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Zero
 * @Date: 2022/4/4 13:48
 * @Description:
 */
@Configuration
public class ComsumerConfig {

    @SneakyThrows
    @Bean(name = "consumer")
    public DefaultMQPushConsumer getDefaultMQPushConsumer() {
        DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer("consumerA");
        pushConsumer.setNamesrvAddr("192.168.88.128:9876");
        pushConsumer.subscribe("topic","*");
        pushConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    String message = new String(msg.getBody());

                    System.out.println("接收到的消息:  "+message);
                }
                /**
                 * 默认情况下  这条消息只能被一个consumer消费  点对点的方式
                 * 这个也叫ACK
                 */
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        return pushConsumer;
    }
}
