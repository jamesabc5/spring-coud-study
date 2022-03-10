package com.james.springbootrabbitmqconsumer.rabbitlistener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;


@RabbitListener(queues = "fanout_rabbit_queue1")
public class FanoutListener {
    @RabbitHandler
    public void fanoutListener(Map Message){
        System.out.println("第一个扇形交换机："+Message);
    }
}
