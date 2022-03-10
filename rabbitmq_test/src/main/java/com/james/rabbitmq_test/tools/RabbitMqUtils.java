package com.james.rabbitmq_test.tools;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMqUtils {
    public static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory=new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("james");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        Connection connection=connectionFactory.newConnection();
        return connection;
    }

    public static void main(String[] args) throws IOException, TimeoutException {

        System.out.println(RabbitMqUtils.getConnection());
    }
}
