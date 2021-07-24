package com.stackroute.userprofileservice.service;

import com.stackroute.userprofileservice.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMqSender {
    public RabbitTemplate rabbitTemplate;

    /*The RabbitTemplate is injected, which accepts and forwards the messages*/
    @Autowired
    public RabbitMqSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${spring.rabbitmq.exchange}")
    String exchange;

    @Value("${spring.rabbitmq.routingkey}")
    String routingKey;

    @Value("${spring.rabbitmq.routingkey2}")
    String routingKey2;

    /*This method sends the book data along with routing key to the queue.*/
    public void send(User user) {
            log.info("User sent:" + user);

        rabbitTemplate.convertAndSend(exchange, routingKey, user);
        rabbitTemplate.convertAndSend(exchange, routingKey2, user.getEmailId());
    }


}
