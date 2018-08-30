package com.eliefly.common.utils;

/**
 * RabbitMQConstants
 *
 * @author eliefly
 * @date 2018-04-16
 */
public interface RabbitMQConstants {

    /**
     * 交换机
     */
    String FANOUT_EXCHANGE = "FANOUT_EXCHANGE";

    /**
     * 队列
     */
    String CLIENT_ONE_QUEUE = "client.one.queue";
    String CLIENT_TWO_QUEUE = "client.two.queue";
    String CLIENT_THREE_QUEUE = "client.three.queue";

    String ACTION_TYPE = "action_type";

    String ACTION_ONE_HELLO = "action_client_one_hello";
    String ACTION_TWO_HELLO = "action_client_two_hello";
    String ACTION_THREE_HELLO = "action_client_three_hello";

}
