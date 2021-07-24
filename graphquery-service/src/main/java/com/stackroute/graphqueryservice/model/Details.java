package com.stackroute.graphqueryservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Details {
    private String innerChild;
    HashMap<String,ContentDetails> content;
}
