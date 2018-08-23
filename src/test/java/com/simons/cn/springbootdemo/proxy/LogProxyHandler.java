package com.simons.cn.springbootdemo.proxy;

import lombok.extern.slf4j.Slf4j;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * JDK动态代理实现示例，需要实现InvocationHandler
 * JDK动态代理只能代理实现了接口的类
 * InvocationHandler：事务处理器，我们接下来写的时间记录和日志打印功能都在此事务处理器中完成
 */
@Slf4j
public class LogProxyHandler implements InvocationHandler {

    private Object target;

    public LogProxyHandler(Object target) {
        super();
        this.target = target;
    }

    /**
     *
     * @param proxy    被代理对象
     * @param method   被代理对象的方法
     * @param args    被代理对象方法的方法参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("日志开始……");
        method.invoke(target);
        log.info("日志结束……");
        return null;
    }
}
