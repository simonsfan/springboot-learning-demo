package com.simons.cn.springbootdemo.exception;

import com.simons.cn.springbootdemo.util.Result;
import com.simons.cn.springbootdemo.util.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 类描述：全局api代码异常处理返回
 */
@ControllerAdvice
public class ApiExceptionHandler {

    @ResponseBody
    @ExceptionHandler
    public Result handleException(Exception e) {
        if (e instanceof GlobalException) {
            GlobalException ge = (GlobalException) e;
            return ResultUtil.success1(ge.getCode(), ge.getMessage());
        }
        return ResultUtil.success1(-1001, "unknown error!");
    }
}
