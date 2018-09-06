package com.eliefly.clientone.rabbitmq.multiple.sender;

import com.eliefly.clientone.rabbitmq.multiple.config.CustomizeRabbitConfiguration;
import com.eliefly.common.clientone.TransferMqConsts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.SimpleResourceHolder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@ConditionalOnBean(CustomizeRabbitConfiguration.class)
@RestController
public class TransferMsgSenderTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransferMsgSenderTest.class);

    @Resource(name = "transferRabbitTemplate")
    private RabbitTemplate transferRabbitTemplate;

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String send(@RequestBody Map<String, String> map) {

        // 根据不同的 virtual host 绑定到 rabbitTemplate
        SimpleResourceHolder.bind(transferRabbitTemplate.getConnectionFactory(), map.get("virtualHost"));

        // rabbitTemplate 发送消息，指定 exchange 和 routingKey
        transferRabbitTemplate.convertAndSend(TransferMqConsts.TRANSFER_TOPIC_EXCHANGE,
                map.get("routingKey"), "hello world.");

        SimpleResourceHolder.unbind(transferRabbitTemplate.getConnectionFactory());

        LOGGER.debug("send success.");

        return String.format("virtualHost: %s, routingKey: %s send success.",
                map.get("virtualHost"), map.get("routingKey"));
    }

}
