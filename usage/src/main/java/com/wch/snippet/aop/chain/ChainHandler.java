package com.wch.snippet.aop.chain;

/**
 * 链式处理器抽象类，实现链式调用
 */
public abstract class ChainHandler {

    /**
     * 链式处理器执行方法
     *
     * @param chain 链式处理器所在处理器链
     */
    public void execute(ProcessChain chain) {
        // 执行处理器本身的逻辑
        this.handleProcess();
        // 通知处理器所在的处理器链继续执行下一个处理器
        chain.proceed();
    }

    protected abstract void handleProcess();
}
