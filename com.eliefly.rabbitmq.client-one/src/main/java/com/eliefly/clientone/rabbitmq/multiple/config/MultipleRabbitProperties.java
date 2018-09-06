package com.eliefly.clientone.rabbitmq.multiple.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * CustomizeRabbitmqPro
 *
 * @author huangfl
 * @since 2018/8/31
 */
@ConfigurationProperties(prefix = "customize.rabbitmq")
public class MultipleRabbitProperties {

    private List<RabbitmqProperties> connections;

    public List<RabbitmqProperties> getConnections() {
        return connections;
    }

    public void setConnections(List<RabbitmqProperties> connections) {
        this.connections = connections;
    }

    public static class RabbitmqProperties {

        private String host;
        private int port;
        private String username;
        private String password;
        private String virtualHost;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getVirtualHost() {
            return virtualHost;
        }

        public void setVirtualHost(String virtualHost) {
            this.virtualHost = virtualHost;
        }
    }
}
