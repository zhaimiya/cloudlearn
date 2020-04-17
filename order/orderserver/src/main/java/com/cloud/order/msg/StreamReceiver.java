package com.cloud.order.msg;

import com.cloud.order.common.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@EnableBinding(StreamClient.class) //定义好的接口
@Slf4j
public class StreamReceiver {

    @StreamListener(Constant.SEND_MSG)
    @SendTo(Constant.RECEIVED_MSG)
    public  String  process(Object val){
        log.info("StreamReceiver msg"+ val);
        return "received msg "+new Date().getTime();
    }

    @StreamListener(Constant.RECEIVED_MSG)
    public  void  processReceiver(Object val){
        log.info("received msg -- --"+ val);
    }
}
