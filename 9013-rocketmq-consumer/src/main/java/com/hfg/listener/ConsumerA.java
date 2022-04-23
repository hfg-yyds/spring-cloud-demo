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
/**
 * 设置消息监听
 * 监听组：监听topic：监听tag(默认监听topic下所有)
 * 监听消费模式:默认负载均衡：CLUSTERING（每一个消息只发给一个消费者）、广播模式：BROADCASTING（发送给所有消费者）
 *
 */
@Component
@RocketMQMessageListener(topic = "first-topic",consumerGroup = "consumerA-group")
@Slf4j
public class ConsumerA implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        //消费topic为first-topic的消息
        System.out.println(message);
    }
}
