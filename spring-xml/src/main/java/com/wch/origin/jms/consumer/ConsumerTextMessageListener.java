package com.wch.origin.jms.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ConsumerTextMessageListener implements MessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerTextMessageListener.class);

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            LOGGER.info("{} consume message: {}", Thread.currentThread().getName(), textMessage.getText());
        } catch (JMSException e) {
            LOGGER.error("consume message failed");
        }
    }
}
