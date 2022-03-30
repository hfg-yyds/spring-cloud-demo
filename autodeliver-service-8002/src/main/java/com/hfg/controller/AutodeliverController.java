package com.hfg.controller;

import com.hfg.config.R;
import com.hfg.entity.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

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
}
