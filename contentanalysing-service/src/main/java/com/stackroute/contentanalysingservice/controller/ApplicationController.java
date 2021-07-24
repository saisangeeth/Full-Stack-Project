package com.stackroute.contentanalysingservice.controller;
import com.stackroute.contentanalysingservice.model.ExternalApiResponse;
import com.stackroute.contentanalysingservice.model.Intent;
import com.stackroute.contentanalysingservice.service.NodeCreationService;
import com.stackroute.contentanalysingservice.service.PageService;
import com.stackroute.contentanalysingservice.service.VideoService;
import io.swagger.annotations.Api;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
//@CrossOrigin
@RestController
@RequestMapping("/api/v1")
@Api(value = "contentanalysing-service")
public class ApplicationController {

    PageService pageService;
    NodeCreationService nodeCreationService;
    VideoService videoService;

    @Autowired
    public ApplicationController(PageService pageService, NodeCreationService nodeCreationService, VideoService videoService) {
        this.pageService = pageService;
        this.nodeCreationService = nodeCreationService;
        this.videoService = videoService;
    }

    @GetMapping("/nodes")
    public ResponseEntity<?> getAllNode() {
        return new ResponseEntity<>(nodeCreationService.getAllKeywords(), HttpStatus.OK);
    }

    @PostMapping("/nodes")
    public ResponseEntity<?> postNodes(@RequestBody Intent intent) {
        nodeCreationService.createAndRelateNodes(intent);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/htmlcontent")
    public ResponseEntity<?> parseHtml(@RequestParam("url") String url) throws IOException {
        System.out.println("In controller "+url);
        Document doc = Jsoup.connect(url).get();
        String result = doc.html();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


//    @GetMapping("/get")
//    public void getApi() throws IOException {
//        videoService.getVideoDetails();
//    }

//    @GetMapping("/value")
//    public List<Response> getData() {
//        return pageService.getTheData();
//    }
}
