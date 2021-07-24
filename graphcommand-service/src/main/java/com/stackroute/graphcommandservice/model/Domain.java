package com.stackroute.graphcommandservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Node
public class Domain {
    @Id
    private String domainName;
    private String createdBy;
    private String programTitle;
    private String description;
    private int duration;
//    private Image image;

    private String name;
    private String type;
    private byte[] picByte;
    private int rating;
    private Status status;
}
