package com.stackroute.questionanswerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class QuestionanswerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuestionanswerServiceApplication.class, args);
	}

}
