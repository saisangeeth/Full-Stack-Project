package com.stackroute.questionanswerservice.service;

import com.stackroute.questionanswerservice.model.Display;
import com.stackroute.questionanswerservice.model.Question;

import java.util.List;

public interface QAService {
    public Question addQuestionForParticularDomain( String questionTitle, String questionBody, String traineeName,String programTitle);

    public Question addAnswerForParticularQuestion(String answerBody, String answeredBy, String questionTitle);

    public List<Display> getAllQuestionsForParticularProgram(String contentTitle);
}
