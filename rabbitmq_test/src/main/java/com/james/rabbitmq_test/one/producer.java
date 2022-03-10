package com.james.rabbitmq_test.one;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

public class producer {
    private static  String QUEUE_NAME="jamesqueue";

    public static void main(String[] args) throws Exception {
        //先获取rabbitmq连接工厂
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost("james");
        factory.setUsername("guest");
        factory.setPassword("guest");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        /*
        * 1.第一个参数表示队列名
        * 2.第二个参数表示是否持久化
        * 3.第三个参数表示是否这个队列只能被一个消费者消费
        * */
        channel.queueDeclare(QUEUE_NAME, false, false,false, null);
        String message="hello world!";
        /*
        * 1.第一个参数表示选用的交换机，当前是默认的
        * 2.第二个参数是队列名
        *
        * */
        for(int i=0;i<=20;i++){
            message=message+i;
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
        }

        System.out.println("消息发送完毕");
        channel.close();
        connection.close();

    }
}
