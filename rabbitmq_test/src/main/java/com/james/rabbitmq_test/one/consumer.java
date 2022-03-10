package com.james.rabbitmq_test.one;

import com.rabbitmq.client.*;

import java.nio.charset.StandardCharsets;

public class consumer {
    private static String QUEUE_NAME="jamesqueue";

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
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        DeliverCallback deliverCallback=(var1, var2)->{
            byte[] bytes=var2.getBody();
            String message=new String(bytes);
            System.out.println(message);
            //反馈消息的消费状态
            channel.basicAck(var2.getEnvelope().getDeliveryTag(),true);

        };
        CancelCallback cancelCallback=var1->{
            System.out.println(var1.getBytes());
        };
        channel.basicConsume(QUEUE_NAME,false,deliverCallback,cancelCallback);
    }
}
