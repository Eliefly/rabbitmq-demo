package com.eliefly.common.rabbitmq.config;

import com.eliefly.common.utils.RabbitMQConstants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitConfig
 *
 * @author eliefly
 * @date 2018-04-16
 */
@Configuration
@EnableRabbit
public class RabbitConfig {

    /**
     * 构建队列，名称，是否持久化之类
     *
     * @return 队列
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

    /**
     * RabbitTemplate，用来发送消息
     *
     * @param connectionFactory 连接工厂？
     * @return RabbitTemplate
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        template.setExchange(RabbitMQConstants.FANOUT_EXCHANGE);
        return template;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
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
