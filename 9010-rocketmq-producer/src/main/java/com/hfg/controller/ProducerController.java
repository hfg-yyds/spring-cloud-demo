package com.hfg.controller;

import com.hfg.config.R;
import com.hfg.config.RocketMQConfiguration;
import com.hfg.config.SendCallbackListener;
import com.hfg.entity.BatchMessageRequest;
import com.hfg.entity.OrderStep;
import com.hfg.utils.ListSplitter;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
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
    private RocketMQConfiguration rocketMQConfiguration;

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @SneakyThrows
    @GetMapping("/sendMessage/{info}")
    @ApiOperation("发送普通消息")
    public R sendMessage(@PathVariable String info) {
        return R.run(()->rocketMQTemplate.convertAndSend("first-topic","你好,Java旅途" + info));
    }

    @GetMapping("/sendSynMessage/{message}")
    @ApiOperation(value = "同步发送消息 topic:syn_tag",notes = "topic:syn_tag")
    public R<SendResult> sendSynMessage(@NotNull(message = "不能为空") @PathVariable String info) {
        Message<String> message = MessageBuilder.withPayload(info).build();
        //设置发送地和消息信息并发送同步消息
        SendResult sendResult = rocketMQTemplate.syncSend(rocketMQConfiguration.getSyn_tag(), message);
        return R.ok(sendResult);
    }

    @GetMapping("/sendAsynMessage/{info}")
    @ApiOperation(value = "发送异步消息 topic:asyn_tag")
    public R<?> sendAsynMessage(@PathVariable String info) {
        Message<String> message = MessageBuilder.withPayload(info)
                .setHeader(RocketMQHeaders.KEYS, 3).build();
        return R.run(()->rocketMQTemplate.asyncSend(rocketMQConfiguration.getAsyn_tag(),
                message, new SendCallbackListener(33)));
    }

    @GetMapping("/sendOneWayMessage/{info}")
    @ApiOperation("发送单向消息,不关注发送结果,一般用于记录日志 topic:oneway_tag")
    public R<?> sendOneWayMessage(@PathVariable String info) {
        Message<String> message = MessageBuilder.withPayload(info).setHeader(
                RocketMQHeaders.KEYS, 4
        ).build();
        return R.run(()->rocketMQTemplate.sendOneWay(rocketMQConfiguration.getOneway_tag(),message));
    }

    @ApiOperation(value = "发送包含顺序的单向消息 topic:syn_tag")
    @GetMapping("/sendSequeueMessage/{id}")
    public R<List<SendResult>> sendSequeueMessage(@PathVariable Integer id) {
        List<SendResult> resultList = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            // 处理当前订单唯一标识
            String myId = id + "" + i;
            // 获取当前订单的操作步骤列表
            List<OrderStep> list = OrderStep.buildOrderSteps(myId);
            for (OrderStep orderStep : list) {
                //构建消息
                String message = String.format("order id : %s, desc : %s",
                        orderStep.getId(),orderStep.getDesc());
                Message<String> build = MessageBuilder.withPayload(message).setHeader(
                        RocketMQHeaders.KEYS, orderStep.getId()).build();
                //设置顺序下发
                rocketMQTemplate.setMessageQueueSelector(new MessageQueueSelector() {
                    /**
                     * 设置放入同一个队列的规则
                     * @param mqs 消息列表
                     * @param msg 当前消息
                     * @param arg 比较的关键信息
                     * @return 消息队列
                     */
                    // 根据当前消息的id，使用固定算法获取需要下发的队列
                    // （使用当前id和消息队列个数进行取模获取需要下发的队列，id和队列数量一样时，选择的队列坑肯定一样）
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs,
                                               org.apache.rocketmq.common.message.Message msg, Object arg) {
//                        ,根据当前消息的id，使用固定算法获取需要下发的队列
// （使用当前id和消息队列个数进行取模获取需要下发的队列，id和队列数量一样时，选择的队列坑肯定一样）
                        int queueNum = Integer.valueOf(String.valueOf(arg)) % list.size();
                        log.info(String.format("queueNum : %s, message : %s", queueNum, new String(msg.getBody())));
                        return mqs.get(queueNum);
                    }
                });
                // 设置发送地和消息信息并发送消息（Orderly）
                SendResult sendResult = rocketMQTemplate.syncSendOrderly(rocketMQConfiguration.getSyn_tag(), message, orderStep.getId());
                resultList.add(sendResult);
            }
        }
        return R.ok(resultList);
    }

    @GetMapping("/sendDelayMessage/{info}")
    @ApiOperation(value = "发送延迟消息 topic:syn_tag")
    public R<SendResult> sendDelayMessage(@PathVariable String info) {
        Message<String> message = MessageBuilder.withPayload(info).build();
        // 设置超时和延时推送
        // 超时时针对请求broker然后结果返回给product的耗时
        // 现在RocketMq并不支持任意时间的延时，需要设置几个固定的延时等级，从1s到2h分别对应着等级1到18
        // private String messageDelayLevel = "1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h";
        return R.run(()->rocketMQTemplate.syncSend(rocketMQConfiguration.getSyn_tag(),
                message,1 * 1000l, 4));
    }

    @ApiOperation(value = "发送批量消息 topic:syn_tag")
    @PostMapping("/sendBatchMessage")
    public R<List<SendResult>> sendBatchMessage (@RequestBody BatchMessageRequest request) {
        org.apache.rocketmq.common.message.Message message1 = new org.apache.rocketmq.common.message.Message();
        List<String> list = request.getList();
        List<SendResult> sendResults = new ArrayList<>();
        ArrayList<Message> messages = new ArrayList<>();
        for (String s : list) {
            Message<String> message = MessageBuilder.withPayload(s).build();
            messages.add(message);
        }
        /// 批量下发消息到broker,不支持消息顺序操作，并且对消息体有大小限制（不超过4M) 1024 * 1024 * 4
        ListSplitter<Message> splitter = new ListSplitter<>(messages, 2);
        while (splitter.hasNext()) {
            List<Message> listItem = splitter.next();
            SendResult sendResult = rocketMQTemplate.syncSend(rocketMQConfiguration.getSyn_tag(), listItem);
            sendResults.add(sendResult);
        }
        return R.ok(sendResults);
    }

    @ApiOperation("SQL过滤消息 topic:syn_tag")
    @GetMapping("/sendSqlMessag/{info}")
    public R<List<SendResult>> sendSqlMessag(@PathVariable int id) {
        ArrayList<SendResult> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String messageStr = i+" "+id;
            Message<String> message = MessageBuilder.withPayload(messageStr).setHeader(RocketMQHeaders.KEYS
                    , id).setHeader("money", i).build();
            SendResult sendResult = rocketMQTemplate.syncSend(rocketMQConfiguration.getSyn_tag(), message);
            results.add(sendResult);
        }
        return R.ok(results);
    }

    @ApiOperation("发送事务消息 topic:syn_tag")
    @GetMapping("/sendTransactionMessage/{id}")
    public R sendTransactionMessage(@PathVariable String id) {
        String messageStr = "order id : " + id;
        Message<String> message = MessageBuilder.withPayload(messageStr)
                .setHeader(RocketMQHeaders.KEYS, id)
                .setHeader("money", 10)
                .setHeader(RocketMQHeaders.TRANSACTION_ID, id)
                .build();
        return R.run(()->rocketMQTemplate.sendMessageInTransaction
                (rocketMQConfiguration.getSyn_tag(),message,null));
    }




}
