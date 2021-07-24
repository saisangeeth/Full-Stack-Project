package com.stackroute.userprofileservice.controller;


import com.stackroute.userprofileservice.exception.UserAlreadyExistsException;
import com.stackroute.userprofileservice.exception.UserNotFoundException;
import com.stackroute.userprofileservice.model.User;
import com.stackroute.userprofileservice.model.UserProfile;
import com.stackroute.userprofileservice.service.RabbitMqSender;
import com.stackroute.userprofileservice.service.UserProfileService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin
@RestController
@RequestMapping("/api/v1")
@Api(value = "userprofile-service")
public class UserProfileController {

    private static final Logger logger = LoggerFactory.getLogger(UserProfileController.
    class);
    private UserProfileService userProfileService;
    RabbitMqSender rabbitMqSender;
    @Autowired
    public UserProfileController(UserProfileService userprofileService,RabbitMqSender rabbitMqSender) {
        this.userProfileService = userprofileService;
        this.rabbitMqSender=rabbitMqSender;

    }

    @PostMapping("/user")
    public ResponseEntity<?> registerUserProfile(@RequestBody UserProfile user) {
        logger.info("Adding the User");
        try {
            logger.info("request body"+user);
            UserProfile userResponse = userProfileService.registerUser(user);
            logger.info("service response"+userResponse);
            User user1=new User(user.getEmailId(),user.getPassword(),user.getUserRole());
            logger.info("user1"+user1);
            rabbitMqSender.send(user1);
            return new ResponseEntity<>(userResponse,HttpStatus.OK);
        } catch (UserAlreadyExistsException e) {
            logger.error("user is already exist:" + user.getEmailId());
            return new ResponseEntity<>("User Already Exist",HttpStatus.CONFLICT);

        }
        catch (Exception e) {
            logger.error("something went wrong" + e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserByEmail(@RequestParam("emailId") String emailId) {
        logger.info("Adding the User");
        try {
            UserProfile user = userProfileService.getUserByEmailId(emailId);
            return new ResponseEntity<>(user,HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User Not Found",HttpStatus.CONFLICT);
        }


    }
    @PutMapping("/user")
    public ResponseEntity<?> updateUserByEmail(@RequestBody UserProfile userProfile) {
        logger.info("Updating the User");
        try{
            UserProfile user = userProfileService.updateUserProfileByEmailId(userProfile);
            return new ResponseEntity<>(user,HttpStatus.OK);
        } catch(UserNotFoundException e) {
            return new ResponseEntity<>("User Not Found",HttpStatus.CONFLICT);
        }
    }

}
