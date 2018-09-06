package com.eliefly.clientone.consumer;

import com.eliefly.common.clientfour.ClientFourMqConsts;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 监听消费 client-four 发出的消息。（topic_exchange + routingKey）
 *
 * @author huangfl
 * @since 2018/5/21
 */
@Component
public class FourClientMsgConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(FourClientMsgConsumer.class);

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = ClientFourMqConsts.TOPIC_ONE, durable = "true"),
            exchange = @Exchange(value = ClientFourMqConsts.CLIENT_FOUR_TOPIC_EXCHANGE, type = "topic", durable = "true"),
            key = ClientFourMqConsts.TOPIC_ONE)
    )
    public void receiveMessage(Message message, Channel channel) throws Exception {

        String receivedRoutingKey = message.getMessageProperties().getReceivedRoutingKey();
        LOGGER.debug("receivedRoutingKey: {}", receivedRoutingKey);
        LOGGER.debug("message: {}", message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = ClientFourMqConsts.TOPIC_TWO, durable = "true"),
            exchange = @Exchange(value = ClientFourMqConsts.CLIENT_FOUR_TOPIC_EXCHANGE, type = "topic",
                    durable = "true", ignoreDeclarationExceptions = "true"), key = ClientFourMqConsts.TOPIC_TWO)
    )
    public void receiveMessage2(Message message, Channel channel) throws Exception {

        String receivedRoutingKey = message.getMessageProperties().getReceivedRoutingKey();
        LOGGER.debug("receivedRoutingKey: {}", receivedRoutingKey);
        LOGGER.debug("message: {}", message);
    }
}