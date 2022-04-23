package com.hfg.listener;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @Author: Zero
 * @Date: 2022/4/23 10:29
 * @Description:
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "${rocketmq.consumer.group}",topic =
        "syn_tagA", messageModel = MessageModel.CLUSTERING)
public class ConsumerSyn implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        System.out.println("ConsumerB消费syn_tagA主题的消息");
        System.out.println(message);
    }
}
