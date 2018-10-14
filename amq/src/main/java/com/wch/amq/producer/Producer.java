package com.wch.amq.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Queue;
import javax.jms.Topic;

@Slf4j
@Service
public class Producer {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Resource
    private Queue queue;

    @Resource
    private Topic topic;

    private static int count = 0;

    /**
     * 分别生产一条消息给两个消息模型
     */
    public void sendTextMessage() {

        String queueMessage = "to queue " + count;

        String topicMessage = "to topic " + count++;

        // 发送消息给队列
        log.info("{} produce message: {}", Thread.currentThread().getName(), queueMessage);
        jmsMessagingTemplate.convertAndSend(this.queue, queueMessage);

        // 发送消息给主题
        log.info("{} produce message: {}", Thread.currentThread().getName(), topicMessage);
        jmsMessagingTemplate.convertAndSend(this.topic, topicMessage);
    }
}
