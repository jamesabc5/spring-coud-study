package com.james.rabbitmq_test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerTest {
    public static void main(String[] args) {
        ApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:spring-rabbitmq-consumer.xml");
    }
}
