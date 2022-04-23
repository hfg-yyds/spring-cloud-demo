package com.hfg.config;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.stereotype.Component;

/**
 * @Author: Zero
 * @Date: 2022/4/22 22:21
 * @Description:
 */
@Slf4j
public class SendCallbackListener implements SendCallback {
    private Object info;

    public SendCallbackListener(Object info) {
        this.info = info;
    }

    @Override
    public void onSuccess(SendResult sendResult) {
      log.info("发送成功的消息为"+JSONUtil.toJsonStr(info));
      log.info("消息发送成功:"+JSONUtil.toJsonStr(sendResult));
    }

    @Override
    public void onException(Throwable e) {
        log.error("发送失败的消息为"+JSONUtil.toJsonStr(info));
        log.error("消息发送失败:"+JSONUtil.toJsonStr(e));
    }
}
