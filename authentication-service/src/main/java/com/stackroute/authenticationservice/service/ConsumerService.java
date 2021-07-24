package com.stackroute.authenticationservice.service;

import com.stackroute.authenticationservice.exception.UserAlreadyExistException;
import com.stackroute.authenticationservice.model.User;
import com.stackroute.authenticationservice.repository.AuthenticationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService implements RabbitListenerConfigurer {
    private static Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    private AuthenticationRepository authenticationRepository;

    @Autowired
    public ConsumerService(AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    /*This method will save the Registered users  to MYSQL-DB*/
    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receivedMessage(User user) throws UserAlreadyExistException {

        logger.info("User received is: " + user);
        if(authenticationRepository.findById(user.getEmailId()).isPresent()){
            throw new UserAlreadyExistException("user already exist");
        }
        authenticationRepository.save(user);
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }

}
