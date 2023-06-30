package com.mi.personel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Program: mistore
 * @Description:
 * @Author: Mike
 * @Date 2023/5/8 18:46
 **/
@SpringBootApplication
@MapperScan("com.mi.personel.dao")
@EnableDiscoveryClient
public class PersonApp {
    public static void main(String[] args) {
        SpringApplication.run(PersonApp.class,args);
    }
}
