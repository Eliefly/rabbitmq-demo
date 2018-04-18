package com.eliefly.clientone.consumer;

import com.eliefly.common.rabbitmq.message.MqMessage;
import com.eliefly.common.utils.RabbitMQConstants;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * OneConsumer
 *
 * @author eliefly
 * @date 2018-04-18
 */
@Component
@RabbitListener(queues = RabbitMQConstants.CLIENT_ONE_QUEUE)
public class ClientOneConsumer {

    @RabbitHandler
    public void process(MqMessage message) {
        if (message.getHeader().get(RabbitMQConstants.ACTION_TYPE).equals("one_add")) {
            processOneAddMessage(message);
        }
    }

    private void processOneAddMessage(MqMessage message) {
        String msg = (String) message.getBody();
        System.out.println(msg);
    }

}