package com.wch.test.amq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Queue;
import javax.jms.Topic;

@Service
@EnableScheduling
public class Producer {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Resource
    private Queue queue;

    @Resource
    private Topic topic;

    private static int count = 0;

    private static final Logger log = LoggerFactory.getLogger(Producer.class);

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
