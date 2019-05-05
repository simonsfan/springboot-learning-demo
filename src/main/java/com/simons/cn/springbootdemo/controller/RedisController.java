package com.simons.cn.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目名称：springbootdemo
 * 类名称：com.simons.cn.springbootdemo.controller
 * 类描述：
 * 创建人：simonsfan
 * 创建时间：2018/6/28 19:46
 */
@RestController
public class RedisController {

/*    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/testredis")
    public String testRedis() {
        String rediskey = "key1";
        redisTemplate.opsForValue().set(rediskey, "simionsfan");
        return redisTemplate.opsForValue().get(rediskey);
    }*/
}
