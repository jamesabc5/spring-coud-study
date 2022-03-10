package com.james.rabbitmq_test.one;

import com.rabbitmq.client.*;

/*
* 消息的订阅发布模式  即将信息先发送到交换机  通过交换机将信息发布之绑定的队列
* */
public class Consumer3 {
    private static String QUEUE_NAME="jamesqueue2";
    private final static String EXCHANGE_NAME = "test_exchange_fanout";
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("james");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.basicQos(1);
        //将通道绑定队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //将交换机绑定队列
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");
        DeliverCallback deliverCallback=(var1, var2)->{
            String message=new String(var2.getBody());
            System.out.println(message);
            //反馈消息的消费状态
            channel.basicAck(var2.getEnvelope().getDeliveryTag(),true);

        };
        CancelCallback cancelCallback= var1->{
            System.out.println(var1.getBytes());
        };
        channel.basicConsume(QUEUE_NAME,false,deliverCallback,cancelCallback);
    }
}
