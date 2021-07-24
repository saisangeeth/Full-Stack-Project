package com.stackroute.authenticationservice.controller;

import com.stackroute.authenticationservice.config.JWTTokenGenerator;
import com.stackroute.authenticationservice.exception.UserAlreadyExistException;
import com.stackroute.authenticationservice.exception.UserNotFoundException;
import com.stackroute.authenticationservice.model.User;
import com.stackroute.authenticationservice.service.AuthenticationServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



// @CrossOrigin
@RestController
@RequestMapping("/api/v1/")
@Api(value = "authentication-service")
public class AuthenticationController {
    private AuthenticationServiceImpl authenticationServiceImpl;
    private JWTTokenGenerator jwtTokenGenerator;


    @Autowired
    public AuthenticationController(AuthenticationServiceImpl authenticationServiceImpl, JWTTokenGenerator jwtTokenGenerator) {
        this.authenticationServiceImpl = authenticationServiceImpl;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user){
        ResponseEntity<?> responseEntity;
        try {
            if (user.getEmailId() == null || user.getPassword() == null)
                throw new UserNotFoundException("Email and Password Empty");
            User userDetails = authenticationServiceImpl.findByEmailAndPassword(user.getEmailId(), user.getPassword());
            if (userDetails == null) {
                throw new UserNotFoundException("Email and Password not found");
            }
            if (!(user.getPassword().equals(userDetails.getPassword()))) {
                throw new UserNotFoundException("Email and Password invalid");
            }
            responseEntity = new ResponseEntity<>(jwtTokenGenerator.generateToken(userDetails), HttpStatus.OK);
        }catch (UserNotFoundException e){
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUserCredentials(@RequestBody User user){
        try {
            User savedLoginDetails = authenticationServiceImpl.saveUser(user);
            return new ResponseEntity<>(savedLoginDetails, HttpStatus.CREATED);
        }catch(UserAlreadyExistException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }





}
