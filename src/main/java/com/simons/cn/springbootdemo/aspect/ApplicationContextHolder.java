package com.simons.cn.springbootdemo.aspect;

import com.simons.cn.springbootdemo.service.Weixin.WeixinServiceImpl;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.concurrent.Executors;

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
        Executors.newWorkStealingPool();
        return apc;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.apc = applicationContext;
    }

    public static void main(String[] args) {
        WeixinServiceImpl proxy = ApplicationContextHolder.getApplicationContext().getBean(WeixinServiceImpl.class);
        System.out.println(proxy);
    }
}
