package com.cloud.order.controller;

import com.cloud.order.common.UserProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserProperties userProperties;

    @RequestMapping("/print")
    public String print() {
        System.out.println(userProperties.getName());
        return userProperties.toString();
    }

}
