package com.stackroute.authenticationservice.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OtpService {

    public String OTP = String.valueOf(new Random().nextInt(900000)+100000);

    @Override
    public String toString() {
        return "This is your Learnzilla Authentication Code: " + OTP; //Add some text
    }


}
