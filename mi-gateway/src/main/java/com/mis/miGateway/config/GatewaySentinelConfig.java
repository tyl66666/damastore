package com.mis.miGateway.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import java.util.Collections;
import java.util.List;

/**
 * @Description: gateway_sentinel
 * @Author: 86150
 * @CreateDate: 2023-04-29 上午 8:51
 */
//@Configuration
public class GatewaySentinelConfig {
    private final List<ViewResolver> viewResolvers;
    private final ServerCodecConfigurer serverCodecConfigurer;

    public GatewaySentinelConfig(ObjectProvider<List<ViewResolver>> viewResolverProvider,
                                 ServerCodecConfigurer serverCodecConfigurer){
        this.viewResolvers = viewResolverProvider.getIfAvailable(Collections::emptyList);
        this.serverCodecConfigurer = serverCodecConfigurer;
    }

//    @Bean
//    @Order(Ordered.HIGHEST_PRECEDENCE)
//    public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler(){
//        return new SentinelGatewayBlockExceptionHandler(viewResolvers,serverCodecConfigurer);
//    }
//
//    @Bean
//    @Order(-1)
//    public GlobalFilter sentinelGatewayFilter(){
//        return new SentinelGatewayFilter();
//    }
}
