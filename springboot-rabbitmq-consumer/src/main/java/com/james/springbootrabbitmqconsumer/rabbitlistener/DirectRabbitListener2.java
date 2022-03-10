package com.james.springbootrabbitmqconsumer.rabbitlistener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues="direct_rabbit_queue1")
public class DirectRabbitListener2 {
    @RabbitHandler
    public void process(Map Message){
        System.out.println("第二个消费者收到的"+Message);
    }
}
