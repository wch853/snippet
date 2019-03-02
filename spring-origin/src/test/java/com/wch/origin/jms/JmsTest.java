package com.wch.origin.jms;

import com.wch.origin.jms.producer.AmqSender;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

import javax.annotation.Resource;

@Test
@ContextConfiguration("classpath:spring/spring-jms.xml")
public class JmsTest {

    @Resource
    private AmqSender amqSender;

    public void jmsTest() {
        amqSender.sendTextMessage(0,"test");
    }
}
