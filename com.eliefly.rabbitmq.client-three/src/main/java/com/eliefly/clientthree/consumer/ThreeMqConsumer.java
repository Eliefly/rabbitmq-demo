package com.eliefly.clientthree.consumer;

import com.eliefly.common.rabbitmq.message.MqMessage;
import com.eliefly.common.utils.RabbitMQConstants;
import com.rabbitmq.client.AMQP;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
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

    /**
     * 消息接受
     *
     * @param message 消息
     */
    @RabbitHandler
    public void receiveMessage(MqMessage message, AMQP.Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {

        String headerType = (String) message.getHeader().get(RabbitMQConstants.ACTION_TYPE);

        if (headerType.equals(message.getHeader().get(RabbitMQConstants.ACTION_ONE_HELLO))) {
            processOneAddMessage(message);
        }
    }

    private void processOneAddMessage(MqMessage message) {
        String msg = (String) message.getBody();
        System.out.println("client three recevie: " + msg);
    }

}