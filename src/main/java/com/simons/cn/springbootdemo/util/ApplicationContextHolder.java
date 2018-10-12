package com.simons.cn.springbootdemo.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 类描述：获取Spring代理类（上下文）
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
