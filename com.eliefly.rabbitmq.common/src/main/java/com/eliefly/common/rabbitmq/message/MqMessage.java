package com.eliefly.common.rabbitmq.message;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * MqMessage
 *
 * @author eliefly
 * @date 2018-04-17
 */

public class MqMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private Object body;
    private HashMap<String, Object> header = new HashMap<>();

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public HashMap<String, Object> getHeader() {
        return this.header;
    }

    public void setHeader(Map<String, Object> header) {
        this.header.putAll(header);
    }
}
