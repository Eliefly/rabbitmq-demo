package com.eliefly.clienttwo.consumer;

import com.eliefly.common.clientone.TransferMqConsts;
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
 * 消费
 *
 * @author huangfl
 * @since 2018/5/21
 */
@Component
public class TransferMsgConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransferMsgConsumer.class);

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = TransferMqConsts.TOPIC_ONE, durable = "true"),
            exchange = @Exchange(value = TransferMqConsts.TRANSFER_TOPIC_EXCHANGE, type = "topic",
                    durable = "true"), key = TransferMqConsts.TOPIC_ONE)
    )
    public void receiveMessage1(Message message, Channel channel) throws Exception {

        String receivedRoutingKey = message.getMessageProperties().getReceivedRoutingKey();
        LOGGER.debug("{} receivedRoutingKey: {}", TransferMqConsts.TOPIC_ONE, receivedRoutingKey);
        LOGGER.debug("message: {}", message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = TransferMqConsts.TOPIC_TWO, durable = "true"),
            exchange = @Exchange(value = TransferMqConsts.TRANSFER_TOPIC_EXCHANGE, type = "topic",
                    durable = "true"), key = TransferMqConsts.TOPIC_TWO)
    )
    public void receiveMessage2(Message message, Channel channel) throws Exception {

        String receivedRoutingKey = message.getMessageProperties().getReceivedRoutingKey();
        LOGGER.debug("{} receivedRoutingKey: {}", TransferMqConsts.TOPIC_TWO, receivedRoutingKey);
        LOGGER.debug("message: {}", message);
    }
}