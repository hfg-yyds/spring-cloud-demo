package com.hfg.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Zero
 * @Date: 2022/4/23 00:05
 * @Description:
 */
@Data
public class BatchMessageRequest {
    List<String> list = new ArrayList<>();
}
