package com.simons.cn.springbootdemo.exception;

/**
 * 项目名称：springbootdemo
 * 类名称：com.simons.cn.springbootdemo.exception
 * 类描述：
 * 创建人：simonsfan
 * 创建时间：2018/6/28 16:12
 */

public class GlobalException extends RuntimeException {

    private Integer code;

    public GlobalException(Integer code,String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
