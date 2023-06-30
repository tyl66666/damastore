package com.mis.miSecurity.config;

import com.mis.bean.Memberinfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GenObj {

    @Bean
    public Memberinfo genMember(){
        return new Memberinfo();
    }

}
