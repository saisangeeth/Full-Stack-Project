package com.stackroute.graphcommandservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEnrollment {
     public List<String> userEmails;
     public String domainName;

}
