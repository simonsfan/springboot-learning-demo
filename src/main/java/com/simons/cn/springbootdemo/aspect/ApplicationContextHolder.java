package com.simons.cn.springbootdemo.aspect;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 项目名称：springbootdemo
 * 类名称：com.simons.cn.springbootdemo.aspect
 * 类描述：
 * 创建人：simonsfan
 * 创建时间：2018/8/23 16:38
 */
public class ApplicationContextHolder implements ApplicationContextAware {

    private static ApplicationContext apc;

    public static ApplicationContext getApplicationContext() {
        return apc;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.apc = applicationContext;
    }
}
