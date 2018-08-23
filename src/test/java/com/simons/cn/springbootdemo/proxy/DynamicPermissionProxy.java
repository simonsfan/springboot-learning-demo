package com.simons.cn.springbootdemo.proxy;

import lombok.extern.slf4j.Slf4j;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 基于JDK动态代理实现的权限认证代理类
 */
@Slf4j
public class DynamicPermissionProxy implements InvocationHandler{
    private Object target;

    public DynamicPermissionProxy(Object target) {
        this.target = target;
    }

    /**
     * @param obj    被代理对象
     * @param method 对象方法
     * @param args   方法参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object obj, Method method, Object[] args) throws Throwable {
        log.info("这里是权限认证切面，开始验证……");
        method.invoke(target);
        log.info("这里是权限认证切面，结束验证……");
        return null;
    }
}
