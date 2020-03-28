package com.cloud.order.msg;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MqReceiver {

    // A   @RabbitListener(queues = "myQueue")
    // B 自动创建队列
    // @RabbitListener(queuesToDeclare = @Queue("myQueue"))
    // C自动创建，exchange和队列绑定
    @RabbitListener(bindings = @QueueBinding(value = @Queue("orderQueue"),exchange = @Exchange("orderExchange")))
    public void process(String msg) {
        log.info(msg);
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue("orderQueue"),key = "key1",exchange = @Exchange("orderExchange")))
    public void processKey1(String msg) {
        log.info(msg);
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue("orderQueue"),key = "key2",exchange = @Exchange("orderExchange")))
    public void processKey2(String msg) {
        log.info(msg);
    }
}
