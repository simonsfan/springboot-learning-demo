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
    private String message;

    public GlobalException(Integer code,String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
