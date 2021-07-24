package com.stackroute.userprofileservice.service;


import com.stackroute.userprofileservice.exception.UserAlreadyExistsException;
import com.stackroute.userprofileservice.exception.UserNotFoundException;
import com.stackroute.userprofileservice.model.UserProfile;
import com.stackroute.userprofileservice.model.UserRole;
import com.stackroute.userprofileservice.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private UserProfileRepository userProfileRepository;
    private UserRole userRole;


    @Autowired
    public void UserProfileServiceImpl(UserProfileRepository userprofileRepository) {
        this.userProfileRepository = userprofileRepository;
    }

    @Override
    public UserProfile registerUser(UserProfile user) throws UserAlreadyExistsException {
        Optional<UserProfile> userExists = userProfileRepository.findById(user.getEmailId());

        if (userExists.isPresent()) {
            throw new UserAlreadyExistsException("UserAlreadyExistException");
        }
        return userProfileRepository.save(user);
    }

    @Override
    public UserProfile getUserByEmailId(String email) throws UserNotFoundException {
        Optional<UserProfile> user = userProfileRepository.findById(email);

        if (!user.isPresent()) {
            throw new UserNotFoundException("UserNotFoundException");
        }

        return user.get();
    }

    @Override
    public UserProfile updateUserProfileByEmailId(UserProfile user) throws UserNotFoundException {
        Optional<UserProfile> updateUser = userProfileRepository.findById(user.getEmailId());
        if (!updateUser.isPresent()) {
            throw new UserNotFoundException("UserNotFoundException");

        }

        return userProfileRepository.save(user);
    }
}
