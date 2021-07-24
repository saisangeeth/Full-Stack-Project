package com.stackroute.graphqueryservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NodeTitleDuration {
    private String title;
    private  String durationInMinutes;
}
