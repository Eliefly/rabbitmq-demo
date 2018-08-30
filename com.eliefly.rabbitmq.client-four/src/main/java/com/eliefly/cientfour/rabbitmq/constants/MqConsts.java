package com.eliefly.cientfour.rabbitmq.constants;

/**
 * MqConsts
 *
 * @author huangfl
 * @since 18/5/22
 */
public class MqConsts {

    public static final String TOPIC_EXCHANGE = "TOPIC_EXCHANGE";

    /**
     * 队列名
     */
    public static final String FOUR_QUEUE = "four.client";

    /**
     * routingKey 匹配模式
     */
    public static final String ROUT_KEY_PATTERN = "four.#";

}
