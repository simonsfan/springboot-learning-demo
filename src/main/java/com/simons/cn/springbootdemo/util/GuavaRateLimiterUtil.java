package com.simons.cn.springbootdemo.util;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Service;

/**
 * 项目名称：springbootdemo
 * 类名称：com.simons.cn.springbootdemo.util
 * 类描述：
 * 创建人：simonsfan
 * 创建时间：2018/7/6 18:33
 */
@Service
public class GuavaRateLimiterUtil {

    RateLimiter rateLimiter = RateLimiter.create(5.0);

    /**
     * 获取令牌
     *
     * @return
     */
    public boolean tryAcquire() {
        return rateLimiter.tryAcquire();
    }


}
