package com.cloud.order.controller;

import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {


    //    @HystrixCommand(fallbackMethod = "fallback")
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
//    })
    @HystrixCommand
    @RequestMapping("/getproductlist")
    public String getProductList() {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("http://127.0.0.1:8501/product/list", String.class);

        return response;
    }

    public String fallback() {
        return "稍后再试！";
    }

    public String defaultFallback() {
        return "休息一下，马上回来";
    }
}
