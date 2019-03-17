package com.wch.origin.jmx;

import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;

/**
 * 访问代理
 */
public class HelloProxy {

    public static void main(String[] args) throws IOException, MalformedObjectNameException {
        String host = "localhost";
        String jmxPort = "9875";
        String serviceUrl = "service:jmx:rmi:///jndi/rmi://" + host + ":" + jmxPort + "/jmxrmi";
        ObjectName objectName = new ObjectName("com.wch.base.jmx:name=Hello");

        // 服务地址
        JMXServiceURL jmxServiceURL = new JMXServiceURL(serviceUrl);
        // 建立JMX连接
        JMXConnector jmxConnector = JMXConnectorFactory.connect(jmxServiceURL);
        // 获取MBeanServer连接
        MBeanServerConnection mBeanServerConnection = jmxConnector.getMBeanServerConnection();
        // 建立相应MBean代理
        HelloMBean proxy = MBeanServerInvocationHandler.newProxyInstance(mBeanServerConnection, objectName,
                HelloMBean.class, false);

        proxy.setAge(21);
        proxy.setName("wch");
        proxy.helloWorld();

        // 关闭JMX连接
        jmxConnector.close();
    }
}