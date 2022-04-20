package com.hfg;

import com.hfg.entity.Cat;
import com.hfg.po.CatPO;
import com.hfg.swift.CatMapper;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: Zero
 * @Date: 2022/4/20 12:34
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestMapStruct {
    public static void main(String[] args) {
        Cat cat = new Cat("1", "哈哈哈", "23");
        CatPO catPO = CatMapper.INSTANCE.carToCarDto(cat);
        System.out.println(catPO.toString());
    }
}
