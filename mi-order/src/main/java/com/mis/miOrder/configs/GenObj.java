package com.mis.miOrder.configs;

import com.mis.bean.Addrinfo;
import com.mis.bean.Memberinfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GenObj {

    @Bean
    public Addrinfo genMember(){
        return new Addrinfo();
    }

}
