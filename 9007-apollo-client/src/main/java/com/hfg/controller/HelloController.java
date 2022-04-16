package com.hfg.controller;

import com.hfg.config.RResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Zero
 * @Date: 2022/4/2 08:59
 * @Description:
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Value("${name}")
    private String name;

    @Value("${address}")
    private String address;

    @Value("${test}")
    private String test;

    @GetMapping("/hello")
    public RResult hello() {
        return RResult.ok().data("name",name).data("address",address).data("test",test);
    }

}
