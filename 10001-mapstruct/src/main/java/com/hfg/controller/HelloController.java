package com.hfg.controller;

import com.hfg.config.R;
import com.hfg.entity.Cat;
import com.hfg.po.CatPO;
import com.hfg.swift.SwiftMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Zero
 * @Date: 2022/4/20 12:42
 * @Description:
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping("/testPOVO")
    public R testPOVO() {
        Cat cat = new Cat("1", "哈哈哈", "23");
        CatPO po = SwiftMapper.INSTANCE.carToCarDto(cat);
        return R.ok(po);
    }
}
