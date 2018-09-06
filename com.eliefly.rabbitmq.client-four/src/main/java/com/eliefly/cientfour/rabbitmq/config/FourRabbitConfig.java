package com.eliefly.cientfour.rabbitmq.config;

import com.eliefly.common.clientfour.ClientFourMqConsts;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
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
     * clientFour 消费队列
     *
     * @return 队列
     */
    @Bean
    Queue fourQueue() {
        return new Queue(ClientFourMqConsts.FOUR_QUEUE, true);
    }

    /**
     * TopicExchange
     *
     * @return
     */
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(ClientFourMqConsts.CLIENT_FOUR_TOPIC_EXCHANGE, true, false);
    }

    /**
     * 绑定基础组件消费队列到交换机
     *
     * @param fourQueue     消息队列
     * @param topicExchange 直通交换机
     * @return 配置结果
     */
    @Bean
    Binding bindingFourQueue2Exchange(Queue fourQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(fourQueue).to(topicExchange).with(ClientFourMqConsts.ROUT_KEY_PATTERN);
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