package com.simons.cn.springbootdemo.exception;

import com.simons.cn.springbootdemo.util.ResultUtil;

/**
 * 类描述：全局api代码异常处理返回
 */
//@ControllerAdvice
public class ApiExceptionHandler {

//    @ResponseBody
//    @ExceptionHandler
    public String handleException(Exception e) {
        if (e instanceof GlobalException) {
            GlobalException ge = (GlobalException) e;
            return ResultUtil.success1(ge.getCode(), ge.getMessage());
        }
        return ResultUtil.success1(-1001, "unknown error!");
    }
}
