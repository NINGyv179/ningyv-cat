package com.ningyv.smallcat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Person01NingyvSmallcatEurekaCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(Person01NingyvSmallcatEurekaCenterApplication.class, args);
    }

}
