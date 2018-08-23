package com.simons.cn.springbootdemo.proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * 聚合方式实现静态代理：代理类中引入业务类
 */
@Slf4j
public class OrderServiceLogProxy2 implements OrderService {

    private OrderServiceImpl orderService;

    public OrderServiceLogProxy2(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @Override
    public void reduceStock() {
        log.info("预减库存开始……");
        orderService.reduceStock();
        log.info("预减库存结束……");
    }
}
