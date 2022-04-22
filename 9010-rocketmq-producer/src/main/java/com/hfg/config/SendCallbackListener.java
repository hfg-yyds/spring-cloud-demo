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
    private int id;

    public SendCallbackListener(int id) {
        this.id = id;
    }

    @Override
    public void onSuccess(SendResult sendResult) {
      log.info("消息发送成功:"+JSONUtil.toJsonStr(sendResult));
    }

    @Override
    public void onException(Throwable e) {
      log.info("消息发送失败:"+JSONUtil.toJsonStr(e));
    }
}
