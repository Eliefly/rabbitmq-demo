package com.eliefly.common.rabbitmq.producer.impl;

import com.eliefly.common.rabbitmq.message.MqMessage;
import com.eliefly.common.rabbitmq.producer.IMqProducer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * MqProducerImpl
 *
 * @author eliefly
 * @date 2018-04-17
 */
@Service("mqProducer")
public class MqProducerImpl implements IMqProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     *
     * @param header 消息头
     * @param data   消息体
     */
    @Override
    public void sendMessage(HashMap<String, Object> header, Object data) {
        MqMessage message = new MqMessage();
        message.setHeader(header);
        message.setBody(data);
        rabbitTemplate.convertAndSend(message);
    }
}
