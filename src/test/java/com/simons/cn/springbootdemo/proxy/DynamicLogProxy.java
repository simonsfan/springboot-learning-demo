package com.simons.cn.springbootdemo.proxy;

import lombok.extern.slf4j.Slf4j;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
/**
 * JDK动态代理实现，必须实现InvocationHandler接口
 * InvocationHandler可以理解为事务处理器，所有切面级别的逻辑都在此完成
 */
@Slf4j
public class DynamicLogProxy implements InvocationHandler {

    //需要代理的对象类
    private Object target;

    public DynamicLogProxy(Object target) {
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
        log.info("这里是日志记录切面，日志开始……");
        //使用方法的反射
        Object invoke = method.invoke(target, args);
        log.info("这里是日志记录切面，日志结束……");
        return invoke;
    }
}
