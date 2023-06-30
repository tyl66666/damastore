package com.mis.miGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MiGatewayApp {
    public static void main(String[] args) {
        SpringApplication.run(MiGatewayApp.class,args);
    }
}
