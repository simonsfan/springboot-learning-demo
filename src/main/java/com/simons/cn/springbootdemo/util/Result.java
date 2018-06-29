package com.simons.cn.springbootdemo.util;


import lombok.Data;

/**
 * 项目名称：springbootdemo
 * 类名称：com.simons.cn.springbootdemo.util
 * 类描述：
 * 创建人：simonsfan
 * 创建时间：2018/6/28 13:56
 */
@Data
public class  Result<T> {
    private Integer code;
    private String msg;
    private T data;
}
