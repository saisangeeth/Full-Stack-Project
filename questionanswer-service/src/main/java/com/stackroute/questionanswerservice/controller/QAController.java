package com.stackroute.questionanswerservice.controller;


import com.stackroute.questionanswerservice.model.Answer;
import com.stackroute.questionanswerservice.model.Question;
import com.stackroute.questionanswerservice.service.QAService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin
@RestController
@RequestMapping("/api/v1")
@Api(value = "questionanswer-service")
public class QAController  {

    public QAService queryService;

    @Autowired
    public QAController(QAService queryService) {
        this.queryService = queryService;
    }


    @PostMapping("/question")
    ResponseEntity<?> postQuestions(@RequestBody Question question) {
        return new ResponseEntity<>(
                queryService.addQuestionForParticularDomain(question.getContentTitle(), question.getQuestionTitle(), question.getQuestionBody(), question.getPublishedBy()),
                HttpStatus.OK);
    }

    @PostMapping("/answer/{questionTitle}")
    ResponseEntity<?> postAnswer(@PathVariable("questionTitle") String questionTitle, @RequestBody Answer answer) {
        return new ResponseEntity<>(
                queryService.addAnswerForParticularQuestion(answer.getAnswerBody(), answer.getAnsweredBy(), questionTitle), HttpStatus.OK);
    }

    @GetMapping("/questions/{contentTitle}")
    ResponseEntity<?> getAllQuestions(@PathVariable("contentTitle") String contentTitle) {
        return new ResponseEntity<>(queryService.getAllQuestionsForParticularProgram(contentTitle), HttpStatus.OK);
    }


}





