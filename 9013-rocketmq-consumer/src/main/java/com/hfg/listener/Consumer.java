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
        "syn_tag", selectorExpression = "syn_tagA||asyn_tagA||oneway_tagA",messageModel = MessageModel.CLUSTERING)
public class Consumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        System.out.println("Consumer消费topic主题的消息");
        System.out.println(message);
    }
}