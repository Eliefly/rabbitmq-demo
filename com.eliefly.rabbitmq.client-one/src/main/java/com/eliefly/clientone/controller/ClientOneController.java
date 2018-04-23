package com.eliefly.clientone.controller;

import com.eliefly.common.rabbitmq.producer.IMqProducer;
import com.eliefly.common.utils.RabbitMQConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * ClientOneController
 *
 * @author eliefly
 * @date 2018-04-19
 */
@RestController
public class ClientOneController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientOneController.class);

    @Autowired
    IMqProducer iMqProducer;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        String msg = "hello, i am client one";
        // 发送消息
        HashMap<String, Object> header = new HashMap<>();
        header.put(RabbitMQConstants.ACTION_TYPE, RabbitMQConstants.ACTION_ONE_HELLO);
        iMqProducer.sendMessage(header, msg);
        LOGGER.info("send message: {} success", msg);
        return msg;
    }
}
