package com.stackroute.graphqueryservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Node
public class Video {
    @Id
    private String url;
    private String title;
    private String durationInMinutes;
    private int videos;
    private int images;

}
