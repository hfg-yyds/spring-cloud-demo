package com.hfg.feign;

import com.hfg.config.R;
import org.springframework.stereotype.Component;

/**
 * @Author: Zero
 * @Date: 2022/3/31 15:22
 * @Description:降级回退逻辑需要定义⼀个类，实现FeignClient接⼝，实现接⼝中的⽅法
 */
@Component
public class FallBackResume implements ResumeFeign {

    @Override
    public R getResumeList() {
        return R.ok().data("客户端降级回退","getResumeList");
    }

    @Override
    public R testOpenFeignRibbon(String id) {
        return R.ok().data("客户端降级回退","testOpenFeignRibbon");
    }
}
