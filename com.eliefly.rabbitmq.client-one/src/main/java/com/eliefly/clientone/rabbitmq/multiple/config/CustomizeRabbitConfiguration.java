package com.eliefly.clientone.rabbitmq.multiple.config;

import com.eliefly.common.clientone.TransferMqConsts;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.SimpleRoutingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * CustomizeRabbitmqConfiguration
 *
 * @author huangfl
 * @since 2018/8/31
 */
@Configuration
@ConditionalOnProperty(prefix = "customize.rabbitmq", name = "enabled")
@EnableConfigurationProperties(MultipleRabbitProperties.class)
public class CustomizeRabbitConfiguration {

    @Autowired
    private MultipleRabbitProperties configs;

    @Bean
    public SimpleRoutingConnectionFactory simpleRoutingConnectionFactory() {

        SimpleRoutingConnectionFactory simpleRoutingConnectionFactory = new SimpleRoutingConnectionFactory();
        Map<Object, ConnectionFactory> targetConnectionFactories = new HashMap<>();

        for (MultipleRabbitProperties.RabbitmqProperties rabbitProperties : configs.getConnections()) {
            CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
            connectionFactory.setHost(rabbitProperties.getHost());
            connectionFactory.setPort(rabbitProperties.getPort());
            connectionFactory.setUsername(rabbitProperties.getUsername());
            connectionFactory.setPassword(rabbitProperties.getPassword());
            connectionFactory.setVirtualHost(rabbitProperties.getVirtualHost());

            targetConnectionFactories.put(rabbitProperties.getVirtualHost(), connectionFactory);
        }
        simpleRoutingConnectionFactory.setTargetConnectionFactories(targetConnectionFactories);
        return simpleRoutingConnectionFactory;
    }

    @Bean
    public RabbitTemplate transferRabbitTemplate(SimpleRoutingConnectionFactory simpleRoutingConnectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(simpleRoutingConnectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setExchange(TransferMqConsts.TRANSFER_TOPIC_EXCHANGE);
        return rabbitTemplate;
    }

    /*@Bean
    topic路由不需要这个路由，接收方直接使用@RabbitListener声明queue，exchange，routingkey
    Queue transferQueue() {
        return new Queue(TransferMqConsts.TRANSFER_QUEUE, true);
    }*/

    /**
     * TopicExchange
     */
    @Bean
    TopicExchange transferTopicExchange() {
        return new TopicExchange(TransferMqConsts.TRANSFER_TOPIC_EXCHANGE, true, false);
    }

    /*@Bean
    topic路由不需要这个路由，接收方直接使用@RabbitListener声明queue，exchange，routingkey
    Binding bindingTransferQueue2Exchange(Queue transferQueue, TopicExchange transferTopicExchange) {
        return BindingBuilder.bind(transferQueue).to(transferTopicExchange).with(TransferMqConsts.ROUT_KEY_PATTERN);
    }*/

    /*@Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory
            (SimpleRoutingConnectionFactory simpleRoutingConnectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(simpleRoutingConnectionFactory);
        // 消息转换器，接收方 @RabbitListener
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return factory;
    }*/

}
