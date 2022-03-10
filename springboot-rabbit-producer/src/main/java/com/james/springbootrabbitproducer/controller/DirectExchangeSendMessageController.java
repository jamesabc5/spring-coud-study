package com.james.springbootrabbitproducer.controller;

import com.james.springbootrabbitproducer.config.DirectRabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping ("/test")
public class DirectExchangeSendMessageController {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @RequestMapping ("/sendDirectMessage")
    public Boolean sendDirectMessage(){
        String messageId=String.valueOf(UUID.randomUUID());
        String messageContent="消费者你好,我是直连交换机的生产者";
        String createTime= LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-DD HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        map.put("messageId",messageId);
        map.put("messageContent",messageContent);
        map.put("createTime",createTime);
        rabbitTemplate.convertAndSend(DirectRabbitConfig.DIRECT_RABBIT_EXCHANGE, DirectRabbitConfig.DIRECT_ROUTING_KEY,map);
        return true;
    }
}
