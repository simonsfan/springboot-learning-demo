package com.simons.cn.springbootdemo.chainresponsibility;

import java.util.List;

/**
 * 责任链模式优化
 */
public class Chain {

    private List<HandlerChain> handlerChainList;

    public Chain(List<HandlerChain> handlerChainList) {
        this.handlerChainList = handlerChainList;
    }

    private int index = 0;

    public void proceed() {
        if (index >= handlerChainList.size()) {
            return;
        }
        handlerChainList.get(index++).execute(this);
    }

}
