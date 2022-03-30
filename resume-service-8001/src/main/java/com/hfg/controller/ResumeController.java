package com.hfg.controller;


import com.hfg.config.R;
import com.hfg.entity.Resume;
import com.hfg.mapper.ResumeMapper;
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

}

