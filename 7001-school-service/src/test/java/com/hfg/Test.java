package com.hfg;

import cn.hutool.core.io.file.FileReader;
import com.hfg.entity.Consumer;
import com.hfg.mapper.ConsumerMapper;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @Author: Zero
 * @Date: 2022/4/19 16:56
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Test {
    @Resource
    private ConsumerMapper consumerMapper;

    @org.junit.Test
    public void test1() {
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
   }
}
