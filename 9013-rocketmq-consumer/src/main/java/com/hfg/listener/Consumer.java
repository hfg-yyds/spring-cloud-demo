package com.hfg.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @Author: Zero
 * @Date: 2022/4/21 19:00
 * @Description:
 */

@Component
@RocketMQMessageListener(topic = "first-topic",consumerGroup = "consumerA-group")
@Slf4j
public class Consumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        //消费topic为first-topic的消息
        System.out.println(message);
    }
}
