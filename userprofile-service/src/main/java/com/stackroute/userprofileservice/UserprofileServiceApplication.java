package com.stackroute.userprofileservice;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableEurekaClient
public class UserprofileServiceApplication {

	@Value("${spring.rabbitmq.queue}")
	String queue;

	@Value("${spring.rabbitmq.exchange}")
	String exchange;

	@Value("${spring.rabbitmq.host}")
	String host;

	@Value("${spring.rabbitmq.username}")
	String userName;

	@Value("${spring.rabbitmq.password}")
	String password;

	@Value("${app.message}")
	String message;

	@Value("${spring.rabbitmq.routingkey}")
	String routingKey;
	@Value("${spring.rabbitmq.routingkey2}")
	String routingKey2;

	//Adding new queue so as to pass and listen to and create new node
	@Value("${spring.rabbitmq.queue2}")
	String userQueue;






	/*A Bean with name Queue is created, where the information from the exchange and sent to consumer */
	@Bean
	Queue queue() {
		return new Queue(queue, true);
	}

	@Bean
	Queue queue2() {
		return new Queue(userQueue, true);
	}

	/*A Bean with name exchange is created which routes the info. to the respective Queue.*/
	@Bean
	Exchange myExchange() {
		return ExchangeBuilder.directExchange(exchange).durable(true).build();
	}


	/*A Bean with name ConnectionFactory is created, that helps in setting up a connection to rabbitmq server
    and configure binding to send the messages */
	@Bean
	public CachingConnectionFactory factory() {
		CachingConnectionFactory factory = new CachingConnectionFactory(host);
		factory.setUsername(userName);
		factory.setPassword(password);
		return factory;
	}


	//Binding created with both the queue

	@Bean
	Binding bindingOne() {
		/**
		 * Add code to bind queue and Exchange
		 */
		return BindingBuilder
				.bind(queue())
				.to(myExchange())
				.with(routingKey)
				.noargs();
	}
	@Bean
	Binding bindingTwo() {
		/**
		 * Add code to bind queue and Exchange
		 */
		return BindingBuilder
				.bind(queue2())
				.to(myExchange())
				.with(routingKey2)
				.noargs();
	}
	/*A Bean with name MessageConverter is created, which converts java objects to JSON Format.*/
	@Bean
	public MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	/*A Bean with name RabbitTemplate is created, which accepts and forwards the messages*/
	@Bean
	public RabbitTemplate template(ConnectionFactory factory) {
		final RabbitTemplate template = new RabbitTemplate(factory);
		template.setMessageConverter(messageConverter());
		return template;
	}


	public static void main(String[] args) {
		SpringApplication.run(UserprofileServiceApplication.class, args);
	}

}
