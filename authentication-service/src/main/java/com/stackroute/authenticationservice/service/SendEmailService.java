package com.stackroute.authenticationservice.service;

//import com.stackroute.authenticationservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;


@Service
public class SendEmailService {
    public OtpService otpService;

    @Autowired
    public SendEmailService(OtpService otpService) {
        this.otpService=otpService;
    }

    public void sendMail(String email) throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("Kraynne44@gmail.com", "Akshit#0424");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("Kraynne44@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        msg.setSubject("Authentication OTP for Learnzilla");
        msg.setContent(otpService.toString(), "text/html");
        msg.setSentDate(new Date());

        Transport.send(msg);
    }

}
