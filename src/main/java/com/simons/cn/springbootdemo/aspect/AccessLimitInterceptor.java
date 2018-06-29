package com.simons.cn.springbootdemo.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 项目名称：springbootdemo
 * 类名称：com.simons.cn.springbootdemo.aspect
 * 类描述：
 * 创建人：simonsfan
 * 创建时间：2018/6/27 17:54
 */
@Component
public class AccessLimitInterceptor extends HandlerInterceptorAdapter {

    private static Logger log = LoggerFactory.getLogger(AccessLimitInterceptor.class);

    /*  实现preHandle方法，返回true表示进入下一个过滤器（如果有下一个）*/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("过滤器被调用");
        //省略具体的逻辑
        return true;
    }
}
