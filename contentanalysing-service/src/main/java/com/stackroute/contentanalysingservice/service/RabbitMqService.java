package com.stackroute.contentanalysingservice.service;

import com.stackroute.contentanalysingservice.model.ExternalApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqService {

    PageService pageService;
    VideoService videoService;

    @Autowired
    public RabbitMqService(PageService pageService, VideoService videoService) {
        this.pageService = pageService;
        this.videoService = videoService;
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void getData(ExternalApiResponse externalApiResponse) {
        if (externalApiResponse.getType().equals("page")) {
            pageService.getTheData(externalApiResponse);
        } else {
            videoService.getVideoDetails(externalApiResponse);
        }
    }
}