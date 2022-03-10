package com.james.rabbitmq_test;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutListener3 {
    @RabbitListener(queues="spring-fanout-queue1")
    public void ListenerQueue(Message message){
        System.out.println(new String(message.getBody()));
    }

}
