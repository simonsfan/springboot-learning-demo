package com.simons.cn.springbootdemo.chainresponsibility;

/**
 * 项目名称：springbootdemo
 * 类名称：com.simons.cn.springbootdemo.chainresponsibility
 * 类描述：
 * 创建人：simonsfan
 * 创建时间：2018/8/23 15:20
 */
public abstract class HandlerChain {

    public void execute(Chain chain){
        //首先调用自己的handlerProcess方法
            handlerProcess();

            chain.proceed();
    }

    protected abstract void handlerProcess();
}
