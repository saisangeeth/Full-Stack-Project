package com.stackroute.graphcommandservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ratings {

    private String domain;
    private String userEmail;
    private int rating;
    private String description;

}
