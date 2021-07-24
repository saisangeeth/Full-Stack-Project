package com.stackroute.userprofileservice.service;

import com.stackroute.userprofileservice.exception.UserAlreadyExistsException;
import com.stackroute.userprofileservice.exception.UserNotFoundException;
import com.stackroute.userprofileservice.model.UserProfile;

public interface UserProfileService {
    UserProfile registerUser(UserProfile user) throws UserAlreadyExistsException;
    UserProfile getUserByEmailId(String emailId) throws UserNotFoundException;
    UserProfile updateUserProfileByEmailId(UserProfile user) throws UserNotFoundException;
}
