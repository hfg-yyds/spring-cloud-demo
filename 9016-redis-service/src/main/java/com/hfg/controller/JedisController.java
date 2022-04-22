package com.hfg.controller;

import com.hfg.config.R;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;

/**
 * @Author: Zero
 * @Date: 2022/4/20 20:47
 * @Description:
 */
@RestController
@RequestMapping("/redis")
public class JedisController {

    @Resource
    private Jedis jedis;

    @RequestMapping(value = "/testString",method = RequestMethod.GET)
    public R testString() {
        String ping = jedis.ping();
        return R.ok(ping);
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.set("phone","10");
        System.out.println(jedis.get("phone"));
    }

}
