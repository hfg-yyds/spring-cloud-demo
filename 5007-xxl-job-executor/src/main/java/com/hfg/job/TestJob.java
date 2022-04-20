package com.hfg.job;

import com.hfg.config.RResult;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: Zero
 * @Date: 2022/4/19 19:44
 * @Description:
 */
@Slf4j
@Component
public class TestJob {

    @XxlJob(value = "test")
    public RResult test () {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
        return RResult.ok().data("","");
    }
}
