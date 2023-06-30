package com.mis.miOrder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
//@EntityScan("com.mis.bean")
@MapperScan({"com.mis.miOrder.dao"})
public class MiOrderApp {
    public static void main(String[] args) {
        SpringApplication.run(MiOrderApp.class,args);
    }
}
