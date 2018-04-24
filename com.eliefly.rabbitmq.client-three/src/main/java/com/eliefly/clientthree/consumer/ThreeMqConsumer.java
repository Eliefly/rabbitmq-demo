package com.eliefly.clientthree.consumer;

import com.eliefly.common.rabbitmq.message.MqMessage;
import com.eliefly.common.utils.RabbitMQConstants;
import com.rabbitmq.client.AMQP;
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
public class ThreeMqConsumer {

    /**
     * 消息接受
     *
     * @param message 消息
     */
//    @RabbitHandler
    @RabbitListener(queues = RabbitMQConstants.CLIENT_THREE_QUEUE)
    public void process(MqMessage message, AMQP.Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag)
            throws Exception {
        String headerType = (String) message.getHeader().get(RabbitMQConstants.ACTION_TYPE);
        if (message.getHeader().get(RabbitMQConstants.ACTION_ONE_HELLO).equals(headerType)) {
            processOneAddMessage(message);
        }
    }

    private void processOneAddMessage(MqMessage message) {
        String msg = (String) message.getBody();
        System.out.println("client three recevie: " + msg);
    }

}