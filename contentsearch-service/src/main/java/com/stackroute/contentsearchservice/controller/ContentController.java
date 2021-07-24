package com.stackroute.contentsearchservice.controller;

import com.stackroute.contentsearchservice.model.ContentData;
import com.stackroute.contentsearchservice.service.GoogleSearchService;
import com.stackroute.contentsearchservice.service.GoogleSearchServiceImpl;
import com.stackroute.contentsearchservice.service.YoutubeSearchService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
//@CrossOrigin
@RestController
@RequestMapping("/api/v1")
@Api(value = "contentsearch-service")
public class ContentController {


    private static final Logger logger = LoggerFactory.getLogger(ContentController.class);

    @Autowired
    private GoogleSearchService googleSearchService;

    @Autowired
    private YoutubeSearchService youtubeSearchService;

    @PostMapping("/content")
    public ResponseEntity<?> importData(@RequestBody ContentData contentData) {
        try {
            googleSearchService.getGoogleSearchApiUrls(contentData);
            youtubeSearchService.getYoutubeVideoApiUrls(contentData);

            return new ResponseEntity<ContentData>(contentData, HttpStatus.OK);
        } catch (Exception exception) {
            logger.error(exception.getMessage());
            return new ResponseEntity<String>(exception.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
