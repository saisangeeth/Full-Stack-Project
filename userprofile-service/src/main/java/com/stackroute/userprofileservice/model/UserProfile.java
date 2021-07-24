package com.stackroute.userprofileservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Document
public class UserProfile {

    @Id
    private String emailId;
    private String fullName;
    private String password;
    private String dob;
    private String gender;
    private String phoneNumber;
    private String[] domain;
    private String[] enrolledProgram;
    private UserRole userRole;
    private String[] coursesCreated;


}
