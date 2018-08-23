package com.simons.cn.springbootdemo.proxy;


import lombok.extern.slf4j.Slf4j;

/**
 * 业务实现类
 */
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Override
    public void reduceStock() {
        try {
            log.info("预减库存中……");
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
