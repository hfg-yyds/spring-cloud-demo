package com.hfg.controller;

import com.hfg.config.R;
import com.hfg.feign.ResumeFeign;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Author: Zero
 * @Date: 2022/3/29 17:30
 * @Description:
 */

@RestController
@RequestMapping("/autodeliver")
public class AutodeliverController {

    @Resource
    private DiscoveryClient discoveryClient;
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private ResumeFeign resumeFeign;
    @GetMapping("/getResumeLists")
    public R getResumeLists() {
        /*List<ServiceInstance> instances = discoveryClient.getInstances("resume-service-8001");
        ServiceInstance instance = instances.get(0);
        String host = instance.getHost();
        int port = instance.getPort();
        String url = "http://"+host+":"+port+"/resume/getResumeList";*/
//        restTemplate.getForObject(url, R.class);

        return restTemplate.getForObject("http://resume-service/resume/getResumeList",R.class);
    }

    @ApiOperation(value = "测试OpenFeign")
    @GetMapping("/getResumeListsByFeign")
    public R getResumeListsByFeign() {
        return resumeFeign.getResumeList();
    }
}
