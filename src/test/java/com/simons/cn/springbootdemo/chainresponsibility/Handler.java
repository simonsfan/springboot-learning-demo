package com.simons.cn.springbootdemo.chainresponsibility;

/**
 * 责任链模式
 */
public abstract class Handler {

    private Handler handler;

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void execute(){
        handlerProcess();
        if(this.handler != null){
            handler.execute();
        }

    }

    protected abstract void handlerProcess();

}
