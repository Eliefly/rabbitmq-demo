package com.eliefly.clientthree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = "com.eliefly")
public class ClientThree {

    public static void main(String[] args) {
        SpringApplication.run(ClientThree.class, args);
    }
}
