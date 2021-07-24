package com.stackroute.graphcommandservice.service;

import com.stackroute.graphcommandservice.model.*;

import java.util.List;
import java.util.Optional;

public interface ProgramService {

    public Program createConceptGraph(Program program, List<ExcelSheetData> excelArray);

    public void createVideoNode(Response response);
    public UserNode createUser(String email);
    public void addUserToCourse(UserEnrollment userEnrollment);
    public void addRatingByTheUser(String domain,String userEmail,int rating,String description);
    public List<String> getProgramsUserIsEnrolledTo(String userEmail);
    public Program createConceptTreeFromArray(Program program,List<ArrayTree> arrayTree);

    public void setTimeForUser(String userEmail,String videoTitle,long time);

    public Long getTimeForUser(String email,String domain);

    public void setTimeForProgram(String userEmail,String domainName,long time);

    public Long getTimeForProgram(String email,String domainName);



}
