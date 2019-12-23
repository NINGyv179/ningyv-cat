package com.ningyv.smallcat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Person05NingyvSmallcatRedisDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(Person05NingyvSmallcatRedisDataApplication.class, args);
    }

}
