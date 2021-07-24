package com.stackroute.learnzillawebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LearnzillaWebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnzillaWebappApplication.class, args);
	}

}
