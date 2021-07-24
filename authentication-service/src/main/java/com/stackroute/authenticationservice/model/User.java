package com.stackroute.authenticationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class User {

    @Id
    private String emailId;
    private String password;
    private UserRole userRole;

//    public User(){
//
//    }
//
//    public User(String emailId, String password, UserRole userRole) {
//        this.emailId = emailId;
//        this.password = password;
//        this.userRole = userRole;
//    }
//
//    public String getEmailId() {
//        return emailId;
//    }
//
//    public void setEmailId(String emailId) {
//        this.emailId = emailId;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public UserRole getUserRole() {
//        return userRole;
//    }
//
//    public void setUserRole(UserRole userRole) {
//        this.userRole = userRole;
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "emailId='" + emailId + '\'' +
//                ", password='" + password + '\'' +
//                ", userRole=" + userRole +
//                '}';
//    }
}
