package com.stackroute.questionanswerservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Display {
    private String contentTitle;
    private String questionTitle;
    private String questionBody;
    private String publishedBy;
    private LocalDateTime questionDate;
    private List<Answer> answers;
}
