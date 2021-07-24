package com.stackroute.contentanalysingservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExternalApiVideoFilter {
    private String VideoTitle;
    private String Duration;
    private String VideoTags;


}
