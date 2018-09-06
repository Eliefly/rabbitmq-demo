package com.eliefly.common.clientfour;

/**
 * MqConsts
 *
 * @author huangfl
 * @since 18/5/22
 */
public class ClientFourMqConsts {

    public static final String CLIENT_FOUR_TOPIC_EXCHANGE = "CLIENT_FOUR_TOPIC";

    /**
     * 队列名
     */
    public static final String FOUR_QUEUE = "four.client";

    /**
     * routingKey 匹配模式
     */
    public static final String ROUT_KEY_PATTERN = "four.#";

    public static final String TOPIC_ONE = "four.topic.test1";
    public static final String TOPIC_TWO = "four.topic.test2";

}
