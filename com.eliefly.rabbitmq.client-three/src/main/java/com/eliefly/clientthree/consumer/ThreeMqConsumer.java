package com.eliefly.clientthree.consumer;

import com.eliefly.common.rabbitmq.message.MqMessage;
import com.eliefly.common.utils.RabbitMQConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * ThreeMqConsumer
 *
 * @author eliefly
 * @date 2018-04-19
 */
@Component
@RabbitListener(queues = RabbitMQConstants.CLIENT_THREE_QUEUE)
public class ThreeMqConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreeMqConsumer.class);


    /**
     * 消息接受
     *
     * @param message 消息
     */
    @RabbitHandler
    public void receiveMessage(MqMessage message) {

        String headerType = (String) message.getHeader().get(RabbitMQConstants.ACTION_TYPE);

        if (RabbitMQConstants.ACTION_ONE_HELLO.equals(headerType)) {
            processOneAddMessage(message);
        }
    }

    private void processOneAddMessage(MqMessage message) {
        String msg = (String) message.getBody();
        LOGGER.debug("client three recevie: {}", msg);

    }

}