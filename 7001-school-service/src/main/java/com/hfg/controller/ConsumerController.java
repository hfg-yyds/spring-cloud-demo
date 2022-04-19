package com.hfg.controller;


import cn.hutool.core.io.file.FileReader;
import com.hfg.config.RResult;
import com.hfg.entity.Consumer;
import com.hfg.mapper.ConsumerMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zero
 * @since 2022-04-19
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    @Resource
    private ConsumerMapper consumerMapper;
    @GetMapping("/test")
    public RResult test() {
        FileReader fileReader = new FileReader("D:\\1.txt");
        String str = fileReader.readString();
        String[] split1 = str.split("#");
        ArrayList<Consumer> list = new ArrayList<>();
        for (String s : split1) {
            String[] split = s.split(",");
            Consumer consumer = new Consumer();
            consumer.setId(split[0]);
            consumer.setMessage(split[1]);
            list.add(consumer);
        }
        for (Consumer consumer : list) {
            consumerMapper.insert(consumer);
        }
        return RResult.ok().data("list",list);
    }
}

