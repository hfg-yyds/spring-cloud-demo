package com.hfg.po;

import com.hfg.entity.Cat;
import lombok.Data;
import com.hfg.swift.SwiftMapper;

/**
 * @Author: Zero
 * @Date: 2022/4/20 12:25
 * @Description:
 */

@Data
public class CatPO {

    private String id;

    private String name;

    private String age;

}
class Test{
    public static void main(String[] args) {
        Cat cat = new Cat("11", "韩","23");
        System.out.println(SwiftMapper.INSTANCE.carToCarDto(cat));
    }
}
