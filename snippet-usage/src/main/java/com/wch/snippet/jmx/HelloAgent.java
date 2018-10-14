package com.wch.snippet.jmx;

import lombok.extern.slf4j.Slf4j;

import javax.management.JMException;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.io.IOException;
import java.lang.management.ManagementFactory;

/**
 * 启动MBean容器，注册MBean实现
 */
@Slf4j
public class HelloAgent {

    public static void main(String[] args) {
        try {
            // 获取MBeanServer，作为MBean容器
            MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
            // ObjectName命名规范为 域名:name=MBean名称，域名和MBean名称可以任意取，用以唯一标识MBean的实现类
            ObjectName helloName = new ObjectName("com.wch.jmx:name=Hello");
            // 在MBeanServer注册此MBean，通过JConsole对MBean是属性和方法进行管理
            mBeanServer.registerMBean(new Hello(), helloName);

            System.in.read();
        } catch (JMException | IOException e) {
            log.error(e.getMessage());
        }
    }
}