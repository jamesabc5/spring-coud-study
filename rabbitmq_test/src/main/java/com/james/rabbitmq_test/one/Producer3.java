package com.james.rabbitmq_test.one;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

//消息的路由模式  将消息先发送到交换机
public class Producer3 {
    //private final  static  String str="insert";
    private final static String EXCHANGE_NAME = "test_exchange_direct";
    public static void main(String[] args) throws  Exception {
        //先获取rabbitmq连接工厂
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost("james");
        factory.setUsername("guest");
        factory.setPassword("guest");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");
        String message="hello world";
        String str="insert";
        for(int i=0;i<=10;i++){
            message=message+i;
            int num=i;
            //根据偶数设置不同的routingkey
            if(i>=5){
                str="update";
            }
            //System.out.println(str);
            channel.basicPublish(EXCHANGE_NAME,str,null,message.getBytes());
        }
        channel.close();
        connection.close();
    }
}
