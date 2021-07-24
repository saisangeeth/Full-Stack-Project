package com.stackroute.graphcommandservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Program {
    private String createdBy;
    private String programTitle;
    private String domainName;
    private int duration;
    private String description;
    //    private Image image;
    private String imageName;
    private String imageType;
    private byte[] picByte;
    private int rating;
    private Status status;

}


