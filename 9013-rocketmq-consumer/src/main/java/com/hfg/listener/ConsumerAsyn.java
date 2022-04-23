package com.hfg.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @Author: Zero
 * @Date: 2022/4/23 12:09
 * @Description:
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "${rocketmq.consumer.group}",topic =
        "asyn_tagA", messageModel = MessageModel.CLUSTERING)
public class ConsumerAsyn implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        System.out.println("ConsumerB消费asyn_tagA主题的消息");
        System.out.println(message);
    }
}