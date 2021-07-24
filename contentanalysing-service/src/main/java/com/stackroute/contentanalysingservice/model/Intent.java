package com.stackroute.contentanalysingservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Node
public class Intent { //parent node

    @Id
    private String name; //name of the category node
}
