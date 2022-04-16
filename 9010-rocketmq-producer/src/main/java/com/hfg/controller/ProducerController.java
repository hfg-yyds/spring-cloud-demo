package com.hfg.controller;

import com.hfg.config.RResult;
import com.hfg.pojo.MessageRequest;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Zero
 * @Date: 2022/4/4 12:42
 * @Description:
 */
@RestController
@RequestMapping("/producer")
@Slf4j
public class ProducerController {

    @Resource
    private DefaultMQProducer producer;

    @SneakyThrows
    @GetMapping("/sendMessage/{topic}/{info}")
    @ApiOperation("发送普通消息")
    public RResult sendMessage(@PathVariable String info, @PathVariable String topic) {
        Message message = new Message(topic, info.getBytes(StandardCharsets.UTF_8));
        SendResult sendResult = producer.send(message);
        return RResult.ok().data("sendResult",sendResult);
    }

    @SneakyThrows
    @PostMapping("/sendBatchMessage")
    public RResult sendBatchMessage(@RequestBody MessageRequest messageRequest) {
        String topic = messageRequest.getTopic();
        List<String> messages = messageRequest.getMessages();
        ArrayList<Message> messageList = new ArrayList<>();
        for (String s : messages) {
            Message message = new Message(topic, s.getBytes(StandardCharsets.UTF_8));
            messageList.add(message);
        }
        SendResult sendResult = producer.send(messageList);
        return RResult.ok().data("sendResult",sendResult);
    }

}
