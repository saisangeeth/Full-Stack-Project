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
public class Domain {

    @Id
    private String domainName;
    private String createdBy;
    private String programTitle;
    private String description;
    private int duration;
    private String imageName;
    private String imageType;
    private byte[] picByte;
    private int rating;
    private Status status;
}
