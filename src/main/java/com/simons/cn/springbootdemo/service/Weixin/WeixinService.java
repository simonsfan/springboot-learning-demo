package com.simons.cn.springbootdemo.service.Weixin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 项目名称：springbootdemo
 * 类名称：com.simons.cn.springbootdemo.service.Weixin
 * 类描述：
 * 创建人：simonsfan
 * 创建时间：2018/7/3 10:08
 */
public interface WeixinService {

    void notify(HttpServletRequest request, HttpServletResponse response);

}
