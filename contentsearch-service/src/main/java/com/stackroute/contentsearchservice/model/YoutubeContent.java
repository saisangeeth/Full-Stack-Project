package com.stackroute.contentsearchservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class YoutubeContent {
    private String title;
    private String link;
    private String duration;
}
