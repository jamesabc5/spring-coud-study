package com.james.springbootrabbitmqconsumer.rabbitlistener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "topic_rabbit_queue2")
public class TopicRabbitListener2 {
    @RabbitHandler
    public void topicRabbitListener(Map message){
        System.out.println("主题交换机2"+message);
    }
}
