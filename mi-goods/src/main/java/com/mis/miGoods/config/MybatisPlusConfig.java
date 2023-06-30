package com.mis.miGoods.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Program: yc119_cloud_res_parent
 * @Description:
 * @Author: Mike
 * @Date 2023/4/8 16:40
 **/
@Configuration
public class MybatisPlusConfig {

    /* 新版map */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){

            MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
            interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
            return interceptor;
    }
}
