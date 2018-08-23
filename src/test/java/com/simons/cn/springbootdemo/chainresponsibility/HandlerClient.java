package com.simons.cn.springbootdemo.chainresponsibility;

/**
 * 客户端调用责任链模式
 */
public class HandlerClient {

    static class HandlerA extends Handler{
        @Override
        protected void handlerProcess() {
            System.out.println("handler by a");
        }
    }
  static class HandlerB extends Handler{
        @Override
        protected void handlerProcess() {
            System.out.println("handler by b");
        }
    }
  static class HandlerC extends Handler{
        @Override
        protected void handlerProcess() {
            System.out.println("handler by c");
        }
    }

    public static void main(String[] args) {
        HandlerA handlerA = new HandlerA();
        HandlerB handlerB = new HandlerB();
        HandlerC handlerC = new HandlerC();

        handlerA.setHandler(handlerB);
        handlerB.setHandler(handlerC);

        handlerA.execute();
    }
}
