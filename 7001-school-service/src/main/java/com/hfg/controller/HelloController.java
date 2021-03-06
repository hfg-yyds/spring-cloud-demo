package com.hfg.controller;

import com.hfg.config.RResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Zero
 * @Date: 2022/3/29 09:48
 * @Description:
 */
@RestController
@RequestMapping("/hello")
public class  HelloController {

    @GetMapping("/get")
    public RResult get() {
        return RResult.ok().data("test","Hello,World!");
    }

}
