package com.hfg.feign;

import com.hfg.config.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: Zero
 * @Date: 2022/3/31 14:10
 * @Description:
 */
@FeignClient(name = "resume-service")
public interface ResumeFeign {

    @RequestMapping(value = "/resume/getResumeList",method = RequestMethod.GET)
    public R getResumeList();
}
