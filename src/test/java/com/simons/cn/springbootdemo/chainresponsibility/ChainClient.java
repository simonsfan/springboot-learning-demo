package com.simons.cn.springbootdemo.chainresponsibility;

import java.util.Arrays;
import java.util.List;

/**
 * 项目名称：springbootdemo
 * 类名称：com.simons.cn.springbootdemo.chainresponsibility
 * 类描述：
 * 创建人：simonsfan
 * 创建时间：2018/8/23 15:25
 */
public class ChainClient {

    static class HandlerChainA extends HandlerChain{
        @Override
        protected void handlerProcess() {
            System.out.println("handler by a");
        }
    }
    static class HandlerChainB extends HandlerChain{
        @Override
        protected void handlerProcess() {
            System.out.println("handler by b");
        }
    }
    static class HandlerChainC extends HandlerChain{
        @Override
        protected void handlerProcess() {
            System.out.println("handler by c");
        }
    }

    /**
     * 优化责任链模式
     *
     * @param args
     */
    public static void main(String[] args) {
        List<HandlerChain> handlerList = Arrays.asList(new HandlerChainA(),new HandlerChainB(),new HandlerChainC());
        Chain chain = new Chain(handlerList);
        chain.proceed();
    }

}
