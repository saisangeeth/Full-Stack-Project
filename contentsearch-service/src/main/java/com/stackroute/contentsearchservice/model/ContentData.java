package com.stackroute.contentsearchservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContentData {
    private String programName;
    private String domainName;
    private List<String> conceptNames;
}
