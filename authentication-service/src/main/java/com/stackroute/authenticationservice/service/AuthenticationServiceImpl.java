package com.stackroute.authenticationservice.service;

import com.stackroute.authenticationservice.exception.UserAlreadyExistException;
import com.stackroute.authenticationservice.exception.UserNotFoundException;
import com.stackroute.authenticationservice.model.User;
import com.stackroute.authenticationservice.repository.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationServiceImpl implements AuthenticatioService{
    private AuthenticationRepository authenticationRepository;

    @Autowired
    public AuthenticationServiceImpl(AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    @Override
    public User findByEmailAndPassword(String emailId, String password) throws UserNotFoundException {
        User authUser= authenticationRepository.findByEmailIdAndPassword(emailId,password);
        if(authUser==null){
            throw new UserNotFoundException("Invalid Id and password");
        }
        return authUser;
    }


    @Override
    public User findByEmailId(String emailId) throws UserNotFoundException {
        User authUser= authenticationRepository.findByEmailId(emailId);
        if(authUser==null){
            throw new UserNotFoundException("Invalid Id and password");
        }
        return authUser;
    }


    @Override
    public User saveUser(User user) throws UserAlreadyExistException {
        if(authenticationRepository.findById(user.getEmailId()).isPresent()){
            throw new UserAlreadyExistException("user already exist");
        }
        return authenticationRepository.save(user);
    }


}
