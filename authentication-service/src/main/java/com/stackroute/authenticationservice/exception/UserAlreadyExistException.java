package com.stackroute.authenticationservice.exception;

public class UserAlreadyExistException extends Exception{
    private String message;


    public UserAlreadyExistException(String message) {
        super(message);
        this.message = message;
    }
    public UserAlreadyExistException(){
    }
}
