package com.eliefly.cientfour.rabbitmq.sender;

import com.eliefly.common.clientfour.ClientFourMqConsts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * FourSenderTest
 *
 * @author huangfl
 * @since 2018/5/21
 */
@RestController
public class FourSenderTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FourSenderTest.class);

    @Resource(name = "clientFourRabbitTemplate")
    private RabbitTemplate clientFourRabbitTemplate;

    @RequestMapping(value = "/send/test1", method = RequestMethod.GET)
    public String send01() {
        clientFourRabbitTemplate.convertAndSend(ClientFourMqConsts.CLIENT_FOUR_TOPIC_EXCHANGE, ClientFourMqConsts.TOPIC_ONE, "hello world 1" + new Date());
        LOGGER.debug("send hello world success.");
        return "success";
    }

    @RequestMapping(value = "/send/test2", method = RequestMethod.GET)
    public String send02() {
        clientFourRabbitTemplate.convertAndSend(ClientFourMqConsts.CLIENT_FOUR_TOPIC_EXCHANGE, ClientFourMqConsts.TOPIC_TWO, "hello world 2" + new Date());
        LOGGER.debug("send hello world success.");
        return "success";
    }
}
