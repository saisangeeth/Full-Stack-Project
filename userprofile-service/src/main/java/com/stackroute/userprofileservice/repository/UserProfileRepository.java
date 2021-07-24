package com.stackroute.userprofileservice.repository;

import com.stackroute.userprofileservice.model.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends MongoRepository<UserProfile,String> {

}
