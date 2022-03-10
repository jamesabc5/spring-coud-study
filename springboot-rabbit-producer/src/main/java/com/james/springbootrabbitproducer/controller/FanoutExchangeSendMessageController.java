package com.james.springbootrabbitproducer.controller;

import com.james.springbootrabbitproducer.config.FanoutRabbitConfig;
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
public class FanoutExchangeSendMessageController {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @RequestMapping ("/sendFanoutMessage")
    public Boolean sendTopicMessage(){
        String messageId=String.valueOf(UUID.randomUUID());
        String messageContent="消费者你好,我是扇形交换机的生产者";
        String createTime= LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-DD HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        map.put("messageId",messageId);
        map.put("messageContent",messageContent);
        map.put("createTime",createTime);
        rabbitTemplate.convertAndSend(FanoutRabbitConfig.FANOUT_RABBIT_EXCHANGE,"",map);
        return true;
    }
}
