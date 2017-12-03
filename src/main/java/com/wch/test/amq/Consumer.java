package com.wch.test.amq;

import com.wch.test.common.CommonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.annotation.JmsListeners;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Service
public class Consumer {

    private static final Logger log = LoggerFactory.getLogger(Consumer.class);

    // 设置监听多个消息模型
    @JmsListeners(value = {
            // 监听queue，设置1-3个消费者
            @JmsListener(destination = CommonConstants.AMQ_QUEUE_TEST_NAME, concurrency = "1-3"),
            // 监听topic，配置特定的监听容器
            @JmsListener(destination = CommonConstants.AMQ_TOPIC_TEST_NAME, containerFactory = "topicListenerContainerFactory")
    })
    public void consumeMessage(Message message) throws JMSException {
        TextMessage textMessage = (TextMessage) message;
        log.info("{} consume message: {}", Thread.currentThread().getName(), textMessage.getText());
    }
}
