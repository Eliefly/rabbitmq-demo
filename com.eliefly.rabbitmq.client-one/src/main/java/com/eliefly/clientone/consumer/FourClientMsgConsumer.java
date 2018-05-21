package com.eliefly.clientone.consumer;

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
 * DimpMsgConsumer
 *
 * @author huangfl
 * @since 2018/5/21
 */
@Component
public class FourClientMsgConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(FourClientMsgConsumer.class);

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "testQueue", durable = "true"),
            exchange = @Exchange(value = "topic_exchange", type = "topic", durable = "true",
                    ignoreDeclarationExceptions = "true"),
            key = "four.test1")
    )
    public void receiveMessage(Message message, Channel channel) throws Exception {

        String receivedRoutingKey = message.getMessageProperties().getReceivedRoutingKey();
        LOGGER.debug("receivedRoutingKey: {}", receivedRoutingKey);
        LOGGER.debug("message: {}", message);
    }
}