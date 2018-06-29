package com.simons.cn.springbootdemo.exception;

import com.simons.cn.springbootdemo.util.Result;
import com.simons.cn.springbootdemo.util.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 项目名称：springbootdemo
 * 类名称：com.simons.cn.springbootdemo
 * 类描述：
 * 创建人：simonsfan
 * 创建时间：2018/6/28 14:30
 */
@ControllerAdvice
public class ExceptionHandle {

    @ResponseBody
    @ExceptionHandler
    public Result handle(Exception e) {
        if (e instanceof GlobalException) {
            GlobalException ge = (GlobalException) e;
            return ResultUtil.success1(ge.getCode(), ge.getMessage());
        }
        return ResultUtil.success1(-1, "system error!");
    }

}
