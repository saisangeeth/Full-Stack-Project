package com.stackroute.userprofileservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Data
@ToString
@NoArgsConstructor
@Document
public class User {
    @Id
    private String emailId;
    private String password;
    private UserRole userRole;
}
