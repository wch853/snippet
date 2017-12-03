package com.wch.test.designPattern.callback;

import com.wch.test.designPattern.CallBack;

public class CallBackDemo {

    public static void main(String[] args) {
        CallBack callBack = new CallBack() {
            @Override
            public void callback(String tName) {
                for (int i = 0; i < 10; i++) {
                    System.out.println(tName + " execute " + i + " ...");
                }
            }
        };

        System.out.println("start...");

        for (int i = 0; i < 20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    callBack.callback(Thread.currentThread().getName());
                }
            }).start();
        }

        try {
            // 主线程挂起10ms
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main end...");
    }
}
