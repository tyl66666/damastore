package com.mi.personel.webconfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Slf4j
@Configuration(proxyBeanMethods = false)
public class WebConfig {

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        HiddenHttpMethodFilter methodFilter = new HiddenHttpMethodFilter();
        methodFilter.setMethodParam("mm");
        return methodFilter;
    }


    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() { //映射文件地址
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                //项目绝对路径 E:\xiangmu\mistore2\mistore
                String absolutePath = new File("").getAbsolutePath();
                String path = absolutePath + "\\mi-index\\src\\main\\resources\\static\\images\\";
                registry.addResourceHandler("/images/**")//映射地址
                        .addResourceLocations("file:" + path);//本地图片保存的地址
            }
        };
    }
}
