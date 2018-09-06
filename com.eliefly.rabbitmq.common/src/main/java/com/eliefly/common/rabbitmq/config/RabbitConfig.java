package com.eliefly.common.rabbitmq.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * RabbitConfig
 *
 * @author eliefly
 * @date 2018-04-16
 */
@Configuration
//@EnableRabbit
public class RabbitConfig {

    @Autowired
    private RabbitProperties rabbitProperties;

    /**
     * 构建队列，名称，是否持久化之类
     *
     * @return 队列r
     */
    @Bean
    Queue clientOneQueue() {
        return new Queue(RabbitMQConstants.CLIENT_ONE_QUEUE, true);
    }

    @Bean
    Queue clientTwoQueue() {
        return new Queue(RabbitMQConstants.CLIENT_TWO_QUEUE, true);
    }

    @Bean
    Queue clientThreeQueue() {
        return new Queue(RabbitMQConstants.CLIENT_THREE_QUEUE, true);
    }

    @Bean
    Queue clientFourQueue() {
        return new Queue(RabbitMQConstants.CLIENT_FOUR_QUEUE, true);
    }

    /**
     * 创建交换机，此处使用Fanout交换机，这种交换机会把消息发送到所有binding到该交换机上的queue，即广播。
     * 其他还有Direct，Topic 交换机。
     *
     * @return 交换机
     */
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(RabbitMQConstants.FANOUT_EXCHANGE, true, false);
    }

    /**
     * 绑定客户端组件队列到交换机, clientOneQueue 对应上面定义的Queue.
     */
    @Bean
    Binding bindingClientOneQueue2exchange(Queue clientOneQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(clientOneQueue).to(fanoutExchange);
    }

    @Bean
    Binding bindingClientTwoQueue2exchange(Queue clientTwoQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(clientTwoQueue).to(fanoutExchange);
    }

    @Bean
    Binding bindingClientThreeQueue2exchange(Queue clientThreeQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(clientThreeQueue).to(fanoutExchange);
    }

    @Bean
    Binding bindingClientFourQueue2exchange(Queue clientFourQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(clientFourQueue).to(fanoutExchange);
    }

    @Bean
    @Primary
    public CachingConnectionFactory springConnectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(rabbitProperties.getHost());
        connectionFactory.setPort(rabbitProperties.getPort());
        connectionFactory.setUsername(rabbitProperties.getUsername());
        connectionFactory.setPassword(rabbitProperties.getPassword());
        connectionFactory.setVirtualHost(rabbitProperties.getVirtualHost());
        return connectionFactory;
    }

    /**
     * RabbitTemplate，用来发送消息
     *
     * @param connectionFactory 连接工厂？
     * @return RabbitTemplate
     */
    @Bean
    public RabbitTemplate rabbitTemplate(@Qualifier("springConnectionFactory") ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        template.setExchange(RabbitMQConstants.FANOUT_EXCHANGE);
        return template;
    }

    @Bean("rabbitListenerContainerFactory")
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory
            (@Qualifier("springConnectionFactory") ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        /*
        添加了配置 acknowledge="auto"，这里来配置mq的确认机制，auto 自动确认，这也是默认缺省的配置。
        特点：消费者挂掉，待ack的消息回归到队列中。消费者抛出异常，消息会不断的被重发，直到处理成功。
        不会丢失消息，即便服务挂掉，没有处理完成的消息会重回队列，但是异常会让消息不断重试。
         */
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        return factory;
    }

//    @Bean
//    public SimpleMessageListenerContainer messageListenerContainer
//            (@Qualifier("springConnectionFactory") ConnectionFactory connectionFactory) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setMessageConverter(new Jackson2JsonMessageConverter());
//        return container;
//    }
//
//    @Bean
//    public RabbitAdmin rabbitAdmin(@Qualifier("springConnectionFactory") ConnectionFactory connectionFactory) {
//        return new RabbitAdmin(connectionFactory);
//    }

}
