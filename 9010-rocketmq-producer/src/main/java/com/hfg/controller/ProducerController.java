package com.hfg.controller;

import com.hfg.config.R;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    private RocketMQTemplate rocketMQTemplate;

    @SneakyThrows
    @GetMapping("/sendMessage/{info}")
    @ApiOperation("发送普通消息")
    public R sendMessage(@PathVariable String info) {
        rocketMQTemplate.convertAndSend("first-topic","你好,Java旅途" + info);
        return R.ok();
    }


}
