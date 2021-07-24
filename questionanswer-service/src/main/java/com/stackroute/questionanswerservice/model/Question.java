package com.stackroute.questionanswerservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class Question {


    private String contentTitle;
    @Id
    private String questionTitle;
    private String questionBody;
    private String publishedBy;
    private LocalDateTime questionDate;
    private List<Answer> answers;
}

