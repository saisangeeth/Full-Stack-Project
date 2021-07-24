package com.stackroute.questionanswerservice.repository;

import com.stackroute.questionanswerservice.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QARepository extends MongoRepository<Question,String> {

}
