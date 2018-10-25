package com.simons.cn.springbootdemo.config;

import com.simons.cn.springbootdemo.aspect.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 项目名称：springbootdemo
 * 类名称：com.simons.cn.springbootdemo.config
 * 类描述：
 * 创建人：simonsfan
 * 创建时间：2018/6/27 16:51
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor accessLimitInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessLimitInterceptor);
    }
}
