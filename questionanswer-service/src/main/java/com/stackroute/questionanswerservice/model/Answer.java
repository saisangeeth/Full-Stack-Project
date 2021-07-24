package com.stackroute.questionanswerservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Answer {
    private String answerBody;
    private String answeredBy;
    private LocalDateTime answerDate;
}

