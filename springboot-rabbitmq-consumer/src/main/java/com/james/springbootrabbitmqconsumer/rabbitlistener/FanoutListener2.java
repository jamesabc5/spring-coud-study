package com.james.springbootrabbitmqconsumer.rabbitlistener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;


@RabbitListener(queues = "fanout_rabbit_queue2")
public class FanoutListener2 {
    @RabbitHandler
    public void fanoutListener(Map Message){
        System.out.println("第二个扇形交换机："+Message);
    }
}
