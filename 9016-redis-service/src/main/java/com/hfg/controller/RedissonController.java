package com.hfg.controller;

import com.hfg.config.R;
import org.redisson.Redisson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;

/**
 * @Author: Zero
 * @Date: 2022/4/20 21:41
 * @Description:
 */
@RestController
@RequestMapping("/redisson")
public class RedissonController {

//    @Resource
//    private RedisTemplate<String,String> redisTemplate;
    @Resource
    private Redisson redisson;
    @Resource
    private Jedis jedis;

    @RequestMapping("/kill")
    public R kill() {
        //从redis后去手机数量
//        int phone = Integer.parseInt(redisTemplate.opsForValue().get("phone"));
        int phone = Integer.parseInt(jedis.get("phone"));
        if (phone>0) {
            phone--;
//            redisTemplate.opsForValue().set("phone",phone+"");
            jedis.set("phone",phone+"");
            System.out.println("现有库存"+jedis.get("phone"));
        } else {
            System.out.println("库存不足");
        }
        return R.ok();
    }

}
