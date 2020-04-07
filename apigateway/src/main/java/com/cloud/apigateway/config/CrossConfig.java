package com.cloud.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CrossConfig {

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.setAllowedOrigins(Arrays.asList("*")); //http:127.0.0.1:9090/，域名
        config.setAllowedHeaders(Arrays.asList("*")); //
        config.setAllowedMethods(Arrays.asList("*"));// get,post..
        config.setMaxAge(300l); //缓存时间限制，在限定时间内，相同跨域请求不做处理

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
