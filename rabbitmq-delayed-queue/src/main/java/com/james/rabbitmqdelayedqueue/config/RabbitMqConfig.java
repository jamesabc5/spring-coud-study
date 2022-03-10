package com.james.rabbitmqdelayedqueue.config;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMqConfig {
    public static final String DELAY_EXCHANGE_NAME = "delayed_exchange";
    public static final String DELAY_QUEUE_NAME = "delay_queue_name";
    public static final String DELAY_ROUTING_KEY = "delay_routing_key";

    @Bean
    public CustomExchange delayExchange(){
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(DELAY_EXCHANGE_NAME,"x-delayed-message",true,false,args);
    }

    @Bean
    public Queue queue(){
        Queue queue=new Queue(DELAY_QUEUE_NAME,true);
        return queue;
    }

    public Binding binding(Queue queue,CustomExchange customExchange){
        return BindingBuilder.bind(queue).to(customExchange).with(DELAY_ROUTING_KEY).noargs();

    }

}
