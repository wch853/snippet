package com.wch.test.aop.aspectj;

/**
 * 模拟用户切换
 */
public class CurrentUserHolder {

    private static final ThreadLocal<String> holder = new ThreadLocal<>();

    public static String get() {
        return holder.get() == null ? "unknown" : holder.get();
    }

    public static void set(String user) {
        holder.set(user);
    }
}
