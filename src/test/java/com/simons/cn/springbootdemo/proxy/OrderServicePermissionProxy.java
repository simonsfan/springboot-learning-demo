package com.simons.cn.springbootdemo.proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * 聚合方式实现静态代理--日志记录功能叠加改造
 */
@Slf4j
public class OrderServicePermissionProxy implements OrderService {

    //注意，这里换成了接口
    private OrderService orderService;

    public OrderServicePermissionProxy(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void reduceStock() {
        log.info("权限验证开始……");
        orderService.reduceStock();
        log.info("权限验证结束……");
    }
}
