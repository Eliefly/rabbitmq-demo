package com.eliefly.cientfour.rabbitmq.sender;

import com.eliefly.cientfour.rabbitmq.constants.MqConsts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * SendControllerTest
 *
 * @author huangfl
 * @since 2018/5/21
 */
@RestController
public class SendControllerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SendControllerTest.class);

    @Resource(name = "clientFourRabbitTemplate")
    private RabbitTemplate clientFourRabbitTemplate;

    @RequestMapping(value = "/send/test1", method = RequestMethod.GET)
    public void send01() {
        clientFourRabbitTemplate.convertAndSend(MqConsts.TOPIC_EXCHANGE, "four.test1", "hello world 1" + new Date());
        LOGGER.debug("send hello world success.");
    }

    @RequestMapping(value = "/send/test2", method = RequestMethod.GET)
    public void send02() {
        clientFourRabbitTemplate.convertAndSend(MqConsts.TOPIC_EXCHANGE, "four.test2", "hello world 2" + new Date());
        LOGGER.debug("send hello world success.");
    }
}
