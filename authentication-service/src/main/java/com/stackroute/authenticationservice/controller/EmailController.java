package com.stackroute.authenticationservice.controller;

import com.stackroute.authenticationservice.model.User;
import com.stackroute.authenticationservice.service.SendEmailService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.stackroute.authenticationservice.config.JWTTokenGenerator;
import com.stackroute.authenticationservice.exception.UserNotFoundException;
import com.stackroute.authenticationservice.service.AuthenticationServiceImpl;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/")
@Api(value = "authentication-service")
public class EmailController {
    private SendEmailService emailService;
    private AuthenticationServiceImpl authenticationServiceImpl;
    private JWTTokenGenerator jwtTokenGenerator;


    public EmailController(SendEmailService emailService, AuthenticationServiceImpl authenticationServiceImpl, JWTTokenGenerator jwtTokenGenerator){
        this.emailService=emailService;
        this.authenticationServiceImpl = authenticationServiceImpl;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> sendEmail(@PathVariable(value="email") String email) throws AddressException,UserNotFoundException, MessagingException, IOException
    {
        User userDetails = authenticationServiceImpl.findByEmailId(email);
        if (userDetails == null) {
                throw new UserNotFoundException("Email not found");
            }
        emailService.sendMail(email);
        return new ResponseEntity<String>(email , HttpStatus.OK);

    }

    @GetMapping ("/otp/{inputOTP}/{email}")
    public ResponseEntity<?> getOTP(@PathVariable(value="inputOTP") String userOTP,@PathVariable(value="email") String email) throws UserNotFoundException
    {
        if(emailService.otpService.toString().substring(emailService.otpService.toString().length()-6).equals(userOTP))
        {
            User userDetails = authenticationServiceImpl.findByEmailId(email);
            if (userDetails == null) {
                throw new UserNotFoundException("Email not found");
            }
            // String otp=emailService.otpService.toString().substring(emailService.otpService.toString().length()-6);
            return new ResponseEntity<>(jwtTokenGenerator.generateToken(userDetails), HttpStatus.ACCEPTED);
//            return emailService.otpService.toString().substring(emailService.otpService.toString().length()-6)
//                    + " and " + userOTP + ". MATCH! Authentication Sucess!";
        }
        else
        {
            return new ResponseEntity<String>( "Authentication Failed!", HttpStatus.CONFLICT);
//            return emailService.otpService.toString().substring(emailService.otpService.toString().length()-6)
//                    + " and " + userOTP + ". NOT A MATCH! Authentication Failed!";
        }
    }
}
