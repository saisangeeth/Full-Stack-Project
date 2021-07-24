package com.stackroute.contentanalysingservice.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,property = "@Id", scope= Response.class)
public class Response { // display in the following format
    private String type;
    private String title;
    private String domainName; //eg. agile
    private String conceptName; //eg. sprint Backlog
    private String url;
    private String intent; // what is the category of the page
    private int confidenceScore; //for getting the confidence score for our intent result
    private String duration; //for getting the time required either to read or watch a video
    private int noOfVideos; // no of videos in the page
    private int noOfImages; //no of images in the page
}

