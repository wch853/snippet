package com.wch.amq;

import com.wch.amq.producer.Producer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import javax.annotation.Resource;

@Test
@SpringBootTest
public class AmqTest extends AbstractTestNGSpringContextTests {

    @Resource
    private Producer producer;

    /**
     * 测试使用activemq发送消息
     */
    public void test() {
        for (int i = 0; i < 100; i++) {
            producer.sendTextMessage();
        }
    }
}
