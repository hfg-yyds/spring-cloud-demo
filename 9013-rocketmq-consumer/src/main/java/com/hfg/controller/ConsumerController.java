package com.hfg.controller;

import com.hfg.config.RResult;
import lombok.SneakyThrows;
import org.apache.rocketmq.client.consumer.DefaultLitePullConsumer;
import org.apache.rocketmq.common.message.MessageQueue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * @Author: Zero
 * @Date: 2022/4/4 12:47
 * @Description:
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Resource(name = "consumer")
    private DefaultLitePullConsumer pullConsumer;

    Integer count = 0;

    @SneakyThrows
    @GetMapping("/getMessage/{topic}")
    public RResult getMessage(@PathVariable String topic) {
        System.out.println("请求进来了");
        Collection<MessageQueue> messageQueues = pullConsumer.fetchMessageQueues(topic);
        for (MessageQueue messageQueue : messageQueues) {
            String queueTopic = messageQueue.getTopic();
        }
        System.out.println("请求出去了");
        return RResult.ok().data("msgsList","msgsList");
    }

}
