package com.james.springbootrabbitproducer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/*
* 扇形交换机（发布订阅模式）
* */
@Configuration
public class FanoutRabbitConfig {
    public static final String  FANOUT_RABBIT_QUEUE1="fanout_rabbit_queue1";
    public static final String  FANOUT_RABBIT_QUEUE2="fanout_rabbit_queue2";
    public static final String  FANOUT_RABBIT_EXCHANGE="fanout_rabbit_exchange";
    @Bean
    public Queue fanoutQueue1(){
        return new Queue(FANOUT_RABBIT_QUEUE1,true);
    }
    @Bean
    public Queue fanoutQueue2(){
        return new Queue(FANOUT_RABBIT_QUEUE2,true);
    }
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(FANOUT_RABBIT_EXCHANGE,true,false);
    }
    @Bean
    public Binding bindingQueue3(){
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
    }
    @Bean
    public Binding bindingQueue4(){
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
    }
}
