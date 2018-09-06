package com.eliefly.cientfour.rabbitmq.config;

import com.eliefly.common.clientfour.ClientFourMqConsts;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TopicExchange 方式的消息配置
 *
 * @author huangfl
 * @since 2018/5/21
 */
@Configuration
@EnableRabbit
public class FourRabbitConfig {

    /**
     * TopicExchange
     *
     * @return
     */
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(ClientFourMqConsts.CLIENT_FOUR_TOPIC_EXCHANGE, true, false);
    }

    @Bean
    public RabbitTemplate clientFourRabbitTemplate(@Qualifier("springConnectionFactory") ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        // 消息转换器，发送方
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        template.setExchange(ClientFourMqConsts.CLIENT_FOUR_TOPIC_EXCHANGE);
        return template;
    }

    /*@Bean
    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(MqConsts.FOUR_QUEUE);
        container.setMessageListener(exampleListener());
        return container;
    }*/

}