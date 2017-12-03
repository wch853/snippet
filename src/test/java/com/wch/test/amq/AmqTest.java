package com.wch.test.amq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AmqTest {

    @Resource
    private Producer producer;

    /**
     * 测试使用activemq发送消息
     */
    @Test
    public void test1() {
        for (int i = 0; i < 100; i++) {
            producer.sendTextMessage();
        }
    }
}
