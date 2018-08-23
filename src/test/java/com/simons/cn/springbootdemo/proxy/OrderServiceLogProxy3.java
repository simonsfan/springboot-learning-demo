package com.simons.cn.springbootdemo.proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * 聚合方式实现静态代理--日志记录功能叠加改造
 */
@Slf4j
public class OrderServiceLogProxy3 implements OrderService {

    //注意，这里换成了接口
    private OrderService orderService;

    public OrderServiceLogProxy3(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void reduceStock() {
        log.info("预减库存开始……");
        orderService.reduceStock();
        log.info("预减库存结束……");
    }
}
