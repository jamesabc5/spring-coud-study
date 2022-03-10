package com.james.rabbitmq_test.one;

import com.rabbitmq.client.*;

/**
 * 路由模式消费者测试
 */
public class Consumer6 {
    private static String QUEUE_NAME="jamesqueue5";
    private final static String EXCHANGE_NAME = "test_exchange_direct";

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("james");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        //绑定队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //队列和交换机绑定  第三个参数是bindingkey 会和前面得到routingkey相对应
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"update");
        channel.basicQos(1);
        DeliverCallback deliverCallback=(var1,var2)->{
            System.out.println(new String(var2.getBody()));
            channel.basicAck(var2.getEnvelope().getDeliveryTag(),true);
        };
        CancelCallback cancelCallback=var1->{
            System.out.println(new String(var1.getBytes()));
        };
        channel.basicConsume(QUEUE_NAME,false,deliverCallback,cancelCallback);
    }
}
