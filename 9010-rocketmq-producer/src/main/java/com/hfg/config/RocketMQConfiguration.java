package com.hfg.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Zero
 * @Date: 2022/4/22 22:05
 * @Description:
 */
@ConfigurationProperties(prefix = "rocketmq.producer")
@Configuration
@Data
public class RocketMQConfiguration {

    private String group;

    private String topic;

    private String syn_tag;

    private String asyn_tag;

    private String oneway_tag;
}
