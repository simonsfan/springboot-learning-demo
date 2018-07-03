package com.simons.cn.springbootdemo.controller;

import com.simons.cn.springbootdemo.exception.GlobalException;
import com.simons.cn.springbootdemo.Enum.ConstantEnum;
import com.simons.cn.springbootdemo.util.Result;
import com.simons.cn.springbootdemo.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 项目名称：springbootdemo
 * 类名称：com.simons.cn.springbootdemo.controller
 * 类描述：
 * 创建人：simonsfan
 * 创建时间：2018/6/28 16:22
 */
@Controller
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @GetMapping(value = "/hello1")
    @ResponseBody
    public Result hello(@RequestParam(value = "age", defaultValue = "50", required = false) Integer age) throws GlobalException {
        if (age < 10) {
            throw new GlobalException(ConstantEnum.LESS10.getCode(), ConstantEnum.LESS10.getMsg());
        } else if (age > 50) {
            throw new GlobalException(ConstantEnum.MORE50.getCode(), ConstantEnum.MORE50.getMsg());
        } else {
            return ResultUtil.success1(0, "success");
        }
    }

}
