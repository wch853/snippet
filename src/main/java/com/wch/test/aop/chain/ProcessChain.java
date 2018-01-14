package com.wch.test.aop.chain;

import java.util.List;

/**
 * 执行链
 */
public class ProcessChain {

    /**
     * 链式处理器集合
     */
    private List<ChainHandler> chainHandlers;

    /**
     * 当前执行顺序
     */
    private int index = 0;

    public ProcessChain(List<ChainHandler> chainHandlers) {
        this.chainHandlers = chainHandlers;
    }

    /**
     * 执行链核心处理方法
     */
    public void proceed() {
        if (index < chainHandlers.size()) {
            // 按序处理链式处理器
            chainHandlers.get(index++).execute(this);
        }
    }
}
