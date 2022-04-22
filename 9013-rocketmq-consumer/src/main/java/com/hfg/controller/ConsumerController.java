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

}
