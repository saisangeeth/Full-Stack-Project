package com.stackroute.contentanalysingservice.service;

import com.stackroute.contentanalysingservice.model.ExternalApiResponse;
import com.stackroute.contentanalysingservice.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class VideoServiceImpl implements VideoService {

    public RestTemplate restTemplate;

    public RabbitTemplate rabbitTemplate;

    @Autowired
    public VideoServiceImpl(RestTemplate restTemplate, RabbitTemplate rabbitTemplate) {
        this.restTemplate = restTemplate;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey2}")
    private String routingkey;


    @Override
    public void getVideoDetails(ExternalApiResponse externalApiResponse) {
        Response responseData;
        responseData = new Response(externalApiResponse.getType(), externalApiResponse.getUrls().get(0).getTitle(), externalApiResponse.getDomain(), externalApiResponse.getConcept(), externalApiResponse.getUrls().get(0).getLink(), "BEGINNER", 5, externalApiResponse.getUrls().get(0).getDuration(), 1, 0);
        rabbitTemplate.convertAndSend(exchange, routingkey, responseData);
        log.info(responseData.toString());
    }
}


