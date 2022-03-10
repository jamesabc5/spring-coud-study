package com.james.springbootrabbitproducer.controller;

import com.james.springbootrabbitproducer.config.DirectRabbitConfig;
import com.james.springbootrabbitproducer.config.TopicRabbitConfig;
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
@RequestMapping("/test")
public class TopicExchangeSendMessageController {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @RequestMapping ("/sendTopicMessage")
    public Boolean sendTopicMessage(){
        String messageId=String.valueOf(UUID.randomUUID());
        String messageContent="消费者你好,我是主题交换机的生产者";
        String createTime= LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-DD HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        map.put("messageId",messageId);
        map.put("messageContent",messageContent);
        map.put("createTime",createTime);
        rabbitTemplate.convertAndSend(TopicRabbitConfig.TOPIC_RABBIT_EXCHANGE, "hunan.weather",map);
        rabbitTemplate.convertAndSend(TopicRabbitConfig.TOPIC_RABBIT_EXCHANGE, "hubei.weather",map);
        return true;
    }
}
