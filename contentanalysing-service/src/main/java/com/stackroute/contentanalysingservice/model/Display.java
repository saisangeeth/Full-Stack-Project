package com.stackroute.contentanalysingservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Display { // display in the following format
    private String domainName; //eg. agile
    private String conceptName; //eg. sprint Backlog
    private String url;
    private String intent; // what is the category of the page
    private float confidenceScore; //for getting the confidence score for our intent result
    private int noOfVideos; // no of videos in the page
    private int noOfImages; //no of images in the page

}

