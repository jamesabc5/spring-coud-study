package com.james.springbootrabbitproducer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
* 直连交换机（路由模式）
* */
@Configuration
public class DirectRabbitConfig {
    private static final String DIRECT_RABBIT_QUEUE1="direct_rabbit_queue1";
    public static final String  DIRECT_RABBIT_EXCHANGE="direct_rabbit_exchange";
    public static final String  DIRECT_ROUTING_KEY="direct_routing_key_test";
    //声名队列
    @Bean
    public Queue directRabbitQueue(){
        /*
        * queue的构造函数中有四个参数,
        * 第一个参数是声名建立的队列名
        * 第二个参数durable,true代表开启此队列的持久化，会将该队列持久化到磁盘中
        * 第三个参数exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        * 第四个autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        * */
        //这里先只是设置队列的持久化即可
        return new Queue(DIRECT_RABBIT_QUEUE1,true);
    }
    //创建直连交换机
    @Bean
    public DirectExchange directRabbitExchange(){
        return new DirectExchange(DIRECT_RABBIT_EXCHANGE,true,false);
    }
    //将直连交换机与队列绑定
    @Bean
    public Binding bindingDirect(){
        return BindingBuilder.bind(directRabbitQueue()).to(directRabbitExchange()).with(DIRECT_ROUTING_KEY);
    }


}
