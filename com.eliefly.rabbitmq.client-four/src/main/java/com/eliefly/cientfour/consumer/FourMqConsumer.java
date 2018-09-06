package com.eliefly.cientfour.consumer;

import com.eliefly.common.rabbitmq.message.MqMessage;
import com.eliefly.common.rabbitmq.config.RabbitMQConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * TwoMqConsumer
 *
 * @author eliefly
 * @date 2018-04-19
 */
@Component
public class FourMqConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(FourMqConsumer.class);

    /**
     * 消息接受
     *
     * @param message 消息
     */
    @RabbitListener(queues = RabbitMQConstants.CLIENT_TWO_QUEUE)
    public void receiveMessage(MqMessage message) {

        String headerType = (String) message.getHeader().get(RabbitMQConstants.ACTION_TYPE);

        if (RabbitMQConstants.ACTION_ONE_HELLO.equals(headerType)) {
            processOneAddMessage(message);
        }
    }

    private void processOneAddMessage(MqMessage message) {
        String msg = (String) message.getBody();
        LOGGER.debug("client four recevie: {}", msg);


    }
}