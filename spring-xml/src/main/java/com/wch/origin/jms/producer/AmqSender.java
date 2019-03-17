package com.wch.origin.jms.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.*;

@Service
public class AmqSender {

    @Resource
    private JmsTemplate jmsTemplate;

    @Resource(name = "queueDestination")
    private Destination queueDestination;

    @Resource(name = "topicDestination")
    private Destination topicDestination;

    private static final Logger log = LoggerFactory.getLogger(AmqSender.class);

    /**
     * 根据消息类型使用不同队列发送消息
     */
    public void sendTextMessage(int messageType, String message) {
        switch (messageType) {
            default:
                constructAndSend(queueDestination, message);
                constructAndSend(topicDestination, message);
        }
    }

    private void constructAndSend(Destination destination, String message) {
        jmsTemplate.send(topicDestination, new MessageCreator() {

            /**
             * 创建TextMessage
             */
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(message);
                log.info("{} produce message: {}", Thread.currentThread().getName(), textMessage.getText());
                return textMessage;
            }
        });
    }
}
