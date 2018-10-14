package com.wch.amq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.annotation.JmsListeners;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Service
@Slf4j
public class Consumer {

    // 设置监听多个消息模型
    @JmsListeners(value = {
            // 监听queue，设置1-3个消费者
            @JmsListener(destination = "${queue.test}", concurrency = "1-3"),
            // 监听topic，配置特定的监听容器
            @JmsListener(destination = "${topic.test}", containerFactory = "topicListenerContainerFactory")
    })
    public void consumeMessage(Message message) throws JMSException {
        TextMessage textMessage = (TextMessage) message;
        log.info("{} consume message: {}", Thread.currentThread().getName(), textMessage.getText());
    }
}
