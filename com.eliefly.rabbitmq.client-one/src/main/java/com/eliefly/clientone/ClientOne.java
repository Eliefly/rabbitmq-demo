package com.eliefly.clientone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.eliefly")
@ComponentScan(basePackages = "com.eliefly")
public class ClientOne {

    public static void main(String[] args) {
        SpringApplication.run(ClientOne.class, args);
    }
}
