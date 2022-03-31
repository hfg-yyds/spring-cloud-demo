package com.hfg.controller;


import com.hfg.config.R;
import com.hfg.entity.Resume;
import com.hfg.mapper.ResumeMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zero
 * @since 2022-03-29
 */
@RestController
@RequestMapping("/resume")
public class ResumeController {
    @Value("${server.port}")
    private String port;
    @Resource
    private ResumeMapper resumeMapper;

    @GetMapping("/getResumeList")
    public R getResumeList() {
        List<Resume> resumeList = resumeMapper.selectList(null);
        return R.ok().data("resumeList",resumeList).data("port",port);
    }

    @HystrixCommand(
            threadPoolKey = "testHystrix",
            //线程池的属性
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "1"), //并发线程数
                    @HystrixProperty(name = "maxQueueSize", value = "20")   //默认线程队列为-1  默认不开启
            },
            fallbackMethod = "fallBackMethod"

    )
    public R testHystrix() {
        return R.ok().data("Results","返回结果,测试正常");
    }

}

