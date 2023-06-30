package com.mis.miGoods;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan({"com.mis.miGoods.dao"})
public class MiGoodsApp {
    public static void main(String[] args) {
        SpringApplication.run(MiGoodsApp.class,args);
    }
}