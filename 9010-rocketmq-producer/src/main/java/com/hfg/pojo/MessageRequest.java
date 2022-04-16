package com.hfg.pojo;

import lombok.Data;

import java.util.List;

/**
 * @Author: Zero
 * @Date: 2022/4/4 13:42
 * @Description:
 */
@Data
public class MessageRequest {

    /**
     * 消息主题
     */
    private String topic;

    /**
     * 消息
     */
    private List<String> messages;

    /**
     * 消息标签
     */
    private String tag;
}
