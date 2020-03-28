package com.cloud.order.controller;

import com.cloud.order.msg.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class SendMsg {

    @Autowired
    private StreamClient streamClient;

    @RequestMapping("/sendmsg")
    public void sendMsg() {
        String msg = "hello " + new Date();
        streamClient.output().send(MessageBuilder.withPayload(msg).build());
    }
}
