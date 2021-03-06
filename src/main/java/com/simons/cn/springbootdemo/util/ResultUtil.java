package com.simons.cn.springbootdemo.util;

import com.google.gson.Gson;

/**
 * 项目名称：springbootdemo
 * 类名称：com.simons.cn.springbootdemo.util
 * 类描述：
 * 创建人：simonsfan
 * 创建时间：2018/6/28 14:48
 */
public class ResultUtil {

    public static String success(Integer code, String msg, Object obj) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(obj);
        return new Gson().toJson(result);
    }

    public static String success1(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return new Gson().toJson(result);
    }


}
