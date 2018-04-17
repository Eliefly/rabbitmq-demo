package com.eliefly.clienttwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan("com.eliefly")
public class ClientTwoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientTwoApplication.class, args);
    }
}
