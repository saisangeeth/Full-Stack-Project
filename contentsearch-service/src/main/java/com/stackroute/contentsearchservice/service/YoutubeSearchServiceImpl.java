package com.stackroute.contentsearchservice.service;

import com.stackroute.contentsearchservice.config.RabbitMqConfiguration;
import com.stackroute.contentsearchservice.model.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class YoutubeSearchServiceImpl implements YoutubeSearchService {


    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;

    @Value("${spring.api.key2}")
    private String apiKey2;

    @Value("${spring.api.query2}")
    private String query2;

    @Value("${spring.api.host2}")
    private String host2;


    @Autowired
    private RabbitMqConfiguration rabbitMqConfiguration;


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(YoutubeSearchServiceImpl.class);
    List<YoutubeContent> externalApiVideoData = new ArrayList<YoutubeContent>();


    @Override
    public String getYoutubeVideoApiUrls(ContentData contentData) {
        for (String concept : contentData.getConceptNames()) {
            RestTemplate rt2 = new RestTemplate();
            String url = "https://google-search3.p.rapidapi.com/youtube-search/?q=" + contentData.getDomainName() + " " + concept;
            HttpHeaders headers = new HttpHeaders();
            headers.set("x-rapidapi-key", apiKey2);
            headers.set("x-rapidapi-host", host2);
            headers.set("useQueryString", query2);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<YoutubeDataFilter> responseEntity = rt2.exchange(url, HttpMethod.GET, entity, YoutubeDataFilter.class);
            List<Items> videoResult = responseEntity.getBody().getItems();
//
            for (Items eachItem : videoResult) {
//

                YoutubeContent youtubeContent = new YoutubeContent(eachItem.getTitle(), "https://www.youtube.com/watch?v=" + eachItem.getId(), eachItem.getDuration());
                externalApiVideoData.add(youtubeContent);
                ExternalApiData externalApiData = new ExternalApiData("video", contentData.getProgramName(), contentData.getDomainName(), concept, externalApiVideoData);
                logger.info(String.valueOf(externalApiVideoData));
                rabbitTemplate.convertAndSend(exchange, routingkey, externalApiData);
                logger.info("sent video Links");
                externalApiVideoData.clear();
//                }

            }

        }
        return "Successfully Sent";
    }
}
