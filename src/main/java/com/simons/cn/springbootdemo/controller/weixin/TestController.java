package com.simons.cn.springbootdemo.controller.weixin;

import com.simons.cn.springbootdemo.aspect.RateLimitAspect;
import com.simons.cn.springbootdemo.util.ResultUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 类描述：RateLimit限流测试（基于注解+AOP）
 * 创建人：simonsfan
 */
@Controller
public class TestController {

    @ResponseBody
    @RateLimitAspect
    @RequestMapping("/test")
    public String test(){
        return ResultUtil.success1(1001, "success").toString();
    }
}
