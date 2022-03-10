package com.james.springbootrabbitproducer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//主题交换机配置
@Configuration
public class TopicRabbitConfig {
    public static final String  TOPIC_RABBIT_QUEUE1="topic_rabbit_queue1";
    public static final String  TOPIC_RABBIT_QUEUE2="topic_rabbit_queue2";
    public static final String  TOPIC_RABBIT_EXCHANGE="topic_rabbit_exchange";
    @Bean
    public Queue topicQueue1(){
        return new Queue(TOPIC_RABBIT_QUEUE1,true);
    }
    @Bean
    public Queue topicQueue2(){
        return new Queue(TOPIC_RABBIT_QUEUE2,true);
    }
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(TOPIC_RABBIT_EXCHANGE,true,false);
    }
    @Bean
    public Binding bindingQueue1(){
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("hunan.*");
    }
    @Bean
    public Binding bindingQueue2(){
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("#.weather");
    }

}
