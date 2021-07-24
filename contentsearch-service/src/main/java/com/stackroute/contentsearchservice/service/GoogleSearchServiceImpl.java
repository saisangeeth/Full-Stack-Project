package com.stackroute.contentsearchservice.service;

import com.stackroute.contentsearchservice.config.RabbitMqConfiguration;
import com.stackroute.contentsearchservice.model.Content;
import com.stackroute.contentsearchservice.model.ContentData;
import com.stackroute.contentsearchservice.model.ExternalApiResponse;
import com.stackroute.contentsearchservice.model.GoogleApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class GoogleSearchServiceImpl implements GoogleSearchService {


    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;

    @Value("${spring.api.key1}")
    private String apiKey1;

    @Value("${spring.api.query1}")
    private String query1;

    @Value("${spring.api.host1}")
    private String host1;

    @Value("${spring.api.message}")
    private String message;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RabbitMqConfiguration rabbitMqConfiguration;


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private YoutubeSearchService youtubeSearchService;

    @Autowired
    private ContentData contentData;

    private static final Logger logger = LoggerFactory.getLogger(GoogleSearchServiceImpl.class);

    List<Content> externalApiLink = new ArrayList<>();


    @Override
    public String getGoogleSearchApiUrls(ContentData contentData) {
        List<ContentData> contentDataList = new ArrayList<ContentData>();
        contentDataList.add(contentData);
        externalApiLink.clear();
        for (String concept : contentData.getConceptNames()) {
            log.info(concept);
            RestTemplate rt = new RestTemplate();
            String url = "https://google-search3.p.rapidapi.com/api/v1/search/q=" + contentData.getDomainName() + " " + concept + "&num=10";
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.set("x-rapidapi-key", apiKey1);
            headers.set("useQueryString", query1);
            headers.set("x-rapidapi-host", host1);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<GoogleApiResponse> responseEntity = rt.exchange(url, HttpMethod.GET, entity, GoogleApiResponse.class);
            List<Content> contentResult = responseEntity.getBody().getResults();
            for (Content eachContent : contentResult) {
                Content content = new Content(eachContent.getTitle(), eachContent.getLink());
                externalApiLink.add(content);
                ExternalApiResponse externalApiResponse = new ExternalApiResponse("page", contentData.getProgramName(), contentData.getDomainName(), concept, externalApiLink);
                logger.info(externalApiResponse.toString());
                rabbitTemplate.convertAndSend(exchange, routingkey, externalApiResponse);
                logger.info("sent Links");
                externalApiLink.clear();

            }

        }
        return "Content successfully created";
    }
}




