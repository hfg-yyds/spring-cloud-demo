package com.hfg.feign;

import com.hfg.config.RResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: Zero
 * @Date: 2022/3/31 14:10
 * @Description:
 */
@FeignClient(name = "resume-service",fallback = FallBackResume.class,path = "/resume")
public interface ResumeFeign {

    @RequestMapping(value = "/getResumeList",method = RequestMethod.GET)
    public RResult getResumeList();

    @RequestMapping(value = "/testOpenFeignRibbon/{id}",method = RequestMethod.GET)
    public RResult testOpenFeignRibbon(@PathVariable(value = "id") String id);
}
