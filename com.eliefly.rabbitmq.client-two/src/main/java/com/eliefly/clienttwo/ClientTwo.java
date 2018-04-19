package com.eliefly.clienttwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = "com.eliefly")
public class ClientTwo {

    public static void main(String[] args) {
        SpringApplication.run(ClientTwo.class, args);
    }
}
