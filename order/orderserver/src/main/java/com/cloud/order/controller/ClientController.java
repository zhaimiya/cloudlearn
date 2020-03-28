package com.cloud.order.controller;

import cloud.product.OrderInfoVo;
import cloud.product.ProductInfoVo;
import cloud.product.client.ProductClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/order")
public class ClientController {



//    @Autowired
//    private LoadBalancerClient loadBalancerClient;
//
//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired(required = false)
    private ProductClient productClient;

    @RequestMapping("/getproductmsg")
    public String getProductMsg() {

//        第一种方式
//        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.getForObject("http://localhost:8788/hello", String.class);

//          第二种方式:loadBalancerClient
//        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
//        String url = String.format("http://%s:%s%s", serviceInstance.getHost(),serviceInstance.getPort(),"/hello");
//        String url = String.format("%s%s", serviceInstance.getUri(), "/hello");
//        log.error(url);
//        log.error("uri: " + serviceInstance.getUri().toString());
//        log.error("host: " + serviceInstance.getHost());
//        log.error("port: " + serviceInstance.getPort());
//        log.error("instanceId: " + serviceInstance.getInstanceId());
//        String response = new RestTemplate().getForObject(url, String.class);

        //第三种方式:@LoadBalanced
//        String response = restTemplate.getForObject("http://PRODUCT/hello", String.class);
//        return response;
        return "";
    }

    @GetMapping("/getprdctmsg")
    public String getMsg() {
        String response = productClient.sayHi();
        return response;
    }

    @PostMapping("/getproductlist")
    public List<ProductInfoVo> getProductList(List<String> productIds) {
        return productClient.getProductList(productIds);
    }

    @PostMapping("/decreasestock")
    public void decreaseStock(List<OrderInfoVo> cartDTOS) {
        productClient.decreaseStock(cartDTOS);
    }
}
