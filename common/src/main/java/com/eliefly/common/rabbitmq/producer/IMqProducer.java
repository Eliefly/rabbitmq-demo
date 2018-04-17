package com.eliefly.common.rabbitmq.producer;

import java.util.HashMap;

/**
 * IMqProducer
 *
 * @author eliefly
 * @date 2018-04-17
 */
public interface IMqProducer {

    /**
     * 发送消息
     *
     * @param header 消息头
     * @param data   消息体
     */
    void sendMessage(HashMap<String, Object> header, Object data);

}
