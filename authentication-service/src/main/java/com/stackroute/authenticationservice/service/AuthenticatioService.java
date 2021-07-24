package com.stackroute.authenticationservice.service;

import com.stackroute.authenticationservice.exception.UserAlreadyExistException;
import com.stackroute.authenticationservice.exception.UserNotFoundException;
import com.stackroute.authenticationservice.model.User;

import java.util.List;

public interface AuthenticatioService {
    User findByEmailAndPassword(String emailId, String password) throws UserNotFoundException;
    User saveUser(User userCredentials) throws UserAlreadyExistException;
    User findByEmailId(String emailId) throws UserNotFoundException;
}
