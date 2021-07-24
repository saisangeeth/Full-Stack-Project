package com.stackroute.questionanswerservice.service;


import com.stackroute.questionanswerservice.model.Answer;
import com.stackroute.questionanswerservice.model.Display;
import com.stackroute.questionanswerservice.model.Question;
import com.stackroute.questionanswerservice.repository.QARepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class QAServiceImpl implements QAService {
    public QARepository qaRepository;

    @Autowired
    public QAServiceImpl(QARepository qaRepository) {
        this.qaRepository = qaRepository;
    }

    @Override
    public Question addQuestionForParticularDomain(String programTitle, String questionTitle, String questionBody, String traineeName) {
//        Optional<Question> findQuestion = queryRepository.findById(domainName);
//        if (findQuestion.get().getQuestionTitle().equals(questionTitle)) {
//            System.out.println(findQuestion.get());
//            return findQuestion.get();
//        } else {
        Question newQuestion = new Question(programTitle, questionTitle, questionBody, traineeName, LocalDateTime.now(), new ArrayList<>());
        log.info(newQuestion.toString());

        qaRepository.save(newQuestion);
        return newQuestion;
//        }
    }

    @Override
    public Question addAnswerForParticularQuestion( String answerBody, String answeredBy, String questionTitle) {
        Optional<Question> findQuestion = qaRepository.findById(questionTitle);
        if (findQuestion.get().getQuestionTitle().equals(questionTitle)) {
            Answer newAnswer = new Answer(answerBody, answeredBy, LocalDateTime.now());
            List<Answer> answersPresent = findQuestion.get().getAnswers();
            answersPresent.add(newAnswer);
            findQuestion.get().setAnswers(answersPresent);
            qaRepository.save(findQuestion.get());
            return findQuestion.get();
        } else {
            return new Question();
        }
    }

    @Override
    public List<Display> getAllQuestionsForParticularProgram(String contentTitle) {
        List<Question> allQuestions = qaRepository.findAll();
        List<Display> sortedQuestion = new ArrayList<>();
        for (Question question : allQuestions) {
            if (question.getContentTitle().equals(contentTitle)) {
                Display newDisplay = new Display(question.getContentTitle(),question.getQuestionTitle(),question.getQuestionBody(),question.getPublishedBy(),
                        question.getQuestionDate(),question.getAnswers());
                sortedQuestion.add(newDisplay);
            }
        }
        Collections.reverse(sortedQuestion);
        return sortedQuestion;
    }


}

