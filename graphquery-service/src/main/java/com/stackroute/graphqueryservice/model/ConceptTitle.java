package com.stackroute.graphqueryservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConceptTitle {
    private String parent;
    private List<String> child;
}
