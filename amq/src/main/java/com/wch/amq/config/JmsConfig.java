package com.wch.amq.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 * JMS配置
 */
@Configuration
public class JmsConfig {

    /**
     * 配置Queue
     */
    @Bean
    public Queue queueTest(@Value("${queue.test}") String queueName) {
        return new ActiveMQQueue(queueName);
    }

    /**
     * 配置Topic
     */
    @Bean
    public Topic topicTest(@Value("${topic.test}") String topicName) {
        return new ActiveMQTopic(topicName);
    }

    /**
     * 配置JmsMessagingTemplate，对JmsTemplate的封装
     */
    @Bean
    public JmsMessagingTemplate jmsMessagingTemplate(JmsTemplate jmsTemplate) {
        return new JmsMessagingTemplate(jmsTemplate);
    }

    /**
     * 为Topic配置MessageListenerContainer，默认为Queue Container
     */
    @Bean
    public JmsListenerContainerFactory<?> topicListenerContainerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        // 设置topic消息类型
        factory.setPubSubDomain(true);
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }
}
