package com.simons.cn.springbootdemo.proxy;

import org.junit.Test;

import java.lang.reflect.Proxy;


public class ProxyTest {
        /**
     * 测试继承方式实现的静态代理
     */
    @Test
    public void testOrderServiceProxy() {
        OrderServiceLogProxy proxy = new OrderServiceLogProxy();
        proxy.reduceStock();
    }

    /**
     * 测试聚合方式实现的静态代理
     */
    @Test
    public void testOrderServiceProxy2() {
        OrderServiceImpl orderService = new OrderServiceImpl();
        OrderServiceLogProxy2 proxy2 = new OrderServiceLogProxy2(orderService);
        proxy2.reduceStock();
    }

    /**
     * 测试聚合方式实现的静态代理-功能叠加
     */
    @Test
    public void testOrderServiceProxy3() {
        OrderServiceImpl orderService = new OrderServiceImpl();
        OrderServiceLogProxy2 logProxy2 = new OrderServiceLogProxy2(orderService);
        OrderServicePermissionProxy permissionProxy = new OrderServicePermissionProxy(logProxy2);
        permissionProxy.reduceStock();
    }

    /**
     * 测试JDK动态代理实现的日志代理类
     */
    @Test
    public void testDynamicLogProxy() {
        OrderServiceImpl orderService = new OrderServiceImpl();
        Class<?> clazz = orderService.getClass();
        DynamicLogProxy logProxyHandler = new DynamicLogProxy(orderService);
        //通过Proxy.newProxyInstance(类加载器, 接口s, 事务处理器Handler) 加载动态代理
        OrderService os = (OrderService) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), logProxyHandler);
        os.reduceStock();
    }

    /**
     * 测试JDK动态代理实现的日志、权限功能代理类
     */
    @Test
    public void testDynamicLogAndPermissProxy() {
        OrderServiceImpl orderService = new OrderServiceImpl();
        Class<?> clazz = orderService.getClass();
        DynamicLogProxy logProxyHandler = new DynamicLogProxy(orderService);
        OrderService os = (OrderService) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), logProxyHandler);
        //注：这里把日志代理类实例对象传入权限认证代理类中
        DynamicPermissionProxy dynamicPermissionProxy = new DynamicPermissionProxy(os);
        OrderService os2 = (OrderService)Proxy.newProxyInstance(os.getClass().getClassLoader(),os.getClass().getInterfaces(),dynamicPermissionProxy);
        os2.reduceStock();
    }


    /**
     * 测试Cglib实现的动态代理-日志功能
     */
    @Test
    public void testGclibDynamicLogProxy(){
        DynamicCglibLogProxy dynamicCglibLogProxy = new DynamicCglibLogProxy();
        OrderServiceImpl orderService = (OrderServiceImpl)dynamicCglibLogProxy.getProxyObj(OrderServiceImpl.class);
        orderService.reduceStock();
    }

}
