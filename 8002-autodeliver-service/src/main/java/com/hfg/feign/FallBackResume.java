package com.hfg.feign;

import com.hfg.config.RResult;
import org.springframework.stereotype.Component;

/**
 * @Author: Zero
 * @Date: 2022/3/31 15:22
 * @Description:降级回退逻辑需要定义⼀个类，实现FeignClient接⼝，实现接⼝中的⽅法
 */
@Component
public class FallBackResume implements ResumeFeign {

    @Override
    public RResult getResumeList() {
        return RResult.ok().data("客户端降级回退","getResumeList");
    }

    @Override
    public RResult testOpenFeignRibbon(String id) {
        return RResult.ok().data("客户端降级回退","testOpenFeignRibbon");
    }
}
