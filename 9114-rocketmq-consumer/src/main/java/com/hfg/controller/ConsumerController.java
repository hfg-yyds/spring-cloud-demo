package com.hfg.controller;

import com.hfg.config.R;
import lombok.SneakyThrows;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Zero
 * @Date: 2022/4/4 12:47
 * @Description:
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Resource(name = "consumer")
    private DefaultMQPushConsumer pushConsumer;

    @SneakyThrows
    @GetMapping("/getMessage/{topic}")
    public R getMessage(@PathVariable String topic) {
        System.out.println("请求进来了");

        System.out.println("请求出去了");
        return R.ok().data("msgsList","msgsList");
    }
}
