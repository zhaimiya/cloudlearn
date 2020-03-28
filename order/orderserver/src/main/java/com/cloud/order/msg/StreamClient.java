package com.cloud.order.msg;

import com.cloud.order.common.Constant;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface StreamClient {
    @Input(Constant.SEND_MSG)
    SubscribableChannel input();

    @Output(Constant.SEND_MSG)
    MessageChannel output();


    @Input(Constant.RECEIVED_MSG)
    SubscribableChannel receiverInput();

    @Output(Constant.RECEIVED_MSG)
    MessageChannel receiverOutput();
}
