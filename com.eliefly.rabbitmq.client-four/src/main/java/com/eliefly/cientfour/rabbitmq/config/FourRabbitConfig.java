package com.eliefly.cientfour.rabbitmq.config;

import com.eliefly.cientfour.rabbitmq.constants.MqConsts;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        return new Queue(MqConsts.FOUR_QUEUE, true);
    }

    /**
     * TopicExchange
     *
     * @return 扇形交换机
     */
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(MqConsts.TOPIC_EXCHANGE, true, false);
    }

    /**
     * 绑定基础组件消费队列到交换机
     *
     * @param fourQueue     消息队列
     * @param topicExchange 直通交换机
     * @return 配置结果
     */
    @Bean
    Binding bindingBaseQueue2exchange(Queue fourQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(fourQueue).to(topicExchange).with(MqConsts.ROUT_KEY_PATTERN);
    }

    @Bean
    public RabbitTemplate clientFourRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        // 消息转换器，发送方
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        template.setExchange(MqConsts.TOPIC_EXCHANGE);
        return template;
    }

//    @Bean
//    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames(MqConsts.FOUR_QUEUE);
//        container.setMessageListener(exampleListener());
//        return container;
//    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        // 消息转换器，接收方 @RabbitListener
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        /*factory.setDefaultRequeueRejected(false);//消息接收异常时禁用拒绝策略
        factory.setAdviceChain(new Advice[] {
                RetryInterceptorBuilder
                .stateless()
                .maxAttempts(5)
                .backOffOptions(1000, 2, 5000)
                .build()
        });*/
        return factory;
    }
}