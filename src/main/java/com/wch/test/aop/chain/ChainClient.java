package com.wch.test.aop.chain;

import java.util.Arrays;

public class ChainClient {

    static class ChainHandlerA extends ChainHandler {
        @Override
        protected void handleProcess() {
            System.out.println("handle a.");
        }
    }

    static class ChainHandlerB extends ChainHandler {
        @Override
        protected void handleProcess() {
            System.out.println("handle b.");
        }
    }

    static class ChainHandlerC extends ChainHandler {
        @Override
        protected void handleProcess() {
            System.out.println("handle c.");
        }
    }

    public static void main(String[] args) {
        ChainHandlerA handlerA = new ChainHandlerA();
        ChainHandlerB handlerB = new ChainHandlerB();
        ChainHandlerC handlerC = new ChainHandlerC();

        ProcessChain chain = new ProcessChain(Arrays.asList(handlerA, handlerB, handlerC));
        chain.proceed();
    }
}
