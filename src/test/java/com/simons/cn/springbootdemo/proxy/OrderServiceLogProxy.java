package com.simons.cn.springbootdemo.proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * 代理类
 */
@Slf4j
public class OrderServiceLogProxy extends OrderServiceImpl{
    @Override
    public void reduceStock() {
        log.info("预减库存开始……");
        super.reduceStock();
        log.info("预减库存结束……");
    }
}
