package com.wch.test.amq;

import com.wch.test.common.CommonConstants;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
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
     *
     * @return Queue
     */
    @Bean
    public Queue queueTest() {
        return new ActiveMQQueue(CommonConstants.AMQ_QUEUE_TEST_NAME);
    }

    /**
     * 配置Topic
     *
     * @return Topic
     */
    @Bean
    public Topic topicTest() {
        return new ActiveMQTopic(CommonConstants.AMQ_TOPIC_TEST_NAME);
    }

    /**
     * 配置JmsMessagingTemplate，对JmsTemplate的封装
     *
     * @param jmsTemplate jmsTemplate
     * @return JmsMessagingTemplate
     */
    @Bean
    public JmsMessagingTemplate jmsMessagingTemplate(JmsTemplate jmsTemplate) {
        return new JmsMessagingTemplate(jmsTemplate);
    }

    /**
     * 为Topic配置MessageListenerContainer，默认为Queue Container
     *
     * @param connectionFactory connectionFactory
     * @return JmsListenerContainerFactory
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
