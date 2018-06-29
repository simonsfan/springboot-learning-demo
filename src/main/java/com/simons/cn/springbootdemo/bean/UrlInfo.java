package com.simons.cn.springbootdemo.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 项目名称：springbootdemo
 * 类名称：com.simons.cn.springbootdemo.bean
 * 类描述：
 * 创建人：simonsfan
 * 创建时间：2018/6/25 18:31
 */
@Component   //把这个类加入扫描
@ConfigurationProperties(prefix = "url")  //加载扫描配置文件yml中url开头下的属性
public class UrlInfo {

    private String payNotifyUrl;

    public String getPayNotifyUrl() {
        return payNotifyUrl;
    }

    public void setPayNotifyUrl(String payNotifyUrl) {
        this.payNotifyUrl = payNotifyUrl;
    }
}
