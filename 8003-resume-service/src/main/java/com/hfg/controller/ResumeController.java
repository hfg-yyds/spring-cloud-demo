package com.hfg.controller;


import com.hfg.config.R;
import com.hfg.entity.Resume;
import com.hfg.mapper.ResumeMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.SneakyThrows;
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

    @GetMapping("/testOpenFeignRibbon/{id}")
    public R testOpenFeignRibbon(@PathVariable String id) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            str.append(i);
        }
        return R.ok().data("字符串",str).data("port",port);
    }


    @SneakyThrows
    @GetMapping("/getResumeList")
    @HystrixCommand(
        // 线程池标识，要保持唯⼀，不唯⼀的话就共⽤了
        threadPoolKey = "getResumeList",
        //线程池的属性
        threadPoolProperties = {
                @HystrixProperty(name = "coreSize", value = "1"), //并发线程数
                @HystrixProperty(name = "maxQueueSize", value = "20")   //默认线程队列为-1  默认不开启
        },
        //commandProperties 熔断的一些细节属性配置
        commandProperties = {
                //设置请求的超时时间,一旦请求超过此时间那么都会按照超时处理
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                //统计窗口时间的设置
                @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds",value = "8000"),
                //统计窗口内的最小请求数
                @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "2"),
                // 统计窗口内错误请求阈值的设置 50%
                @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
                // 自我修复的活动窗口时间
                @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "3000")
        },
        fallbackMethod = "fallBackMethod"
    )
    public R getResumeList() {
        List<Resume> resumeList = resumeMapper.selectList(null);
        Thread.sleep(5000);
        return R.ok().data("port",port).data("resumeList",resumeList);
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

    public R fallBackMethod() {
        return R.ok().data("Fallback","服务降级");
    }


}

