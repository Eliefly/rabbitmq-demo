package com.eliefly.common.rabbitmq.config;

/**
 * RabbitMQConstants
 *
 * @author eliefly
 * @date 2018-04-16
 */
public class RabbitMQConstants {

    /**
     * 交换机
     */
    public static final String FANOUT_EXCHANGE = "MODULE_FANOUT";

    /**
     * 队列
     */
    public static final String CLIENT_ONE_QUEUE = "client.one.queue";
    public static final String CLIENT_TWO_QUEUE = "client.two.queue";
    public static final String CLIENT_THREE_QUEUE = "client.three.queue";
    public static final String CLIENT_FOUR_QUEUE = "client.four.queue";

    public static final String ACTION_TYPE = "action_type";

    public static final String ACTION_ONE_HELLO = "action_client_one_hello";

}
