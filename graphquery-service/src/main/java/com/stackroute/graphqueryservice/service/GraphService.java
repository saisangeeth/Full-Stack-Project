package com.stackroute.graphqueryservice.service;

import com.stackroute.graphqueryservice.model.*;

import java.util.List;

public interface GraphService {
    public List<ConceptTitle> getAllTheConcepts(String domainName);
    public List<String> getConceptOfAnyDomain(String domainName);


    public List<Program> getAllProgramById(String email);
    public List<Program> getCourseForUserEnrolledTO(String email);

//    public List<Content> getTOC(String domainName);

    public List<Content> getTOC(String domainName,String intent);

    public URL getUrlOfVideoTitle(String conceptName, String videoTitle);
    public URL getUrlOfDocumentTitle(String conceptName,String documentTitle);
    public List<Program> getAllTheProgram();
    public List<String> getEmails();
    public String getDocumentForParticularConcept(String conceptName, String intent);
    public List<String> getAllConceptsForParticularDomain(String domainName);
    public Program getProgramInFromDomainName(String domainName);
}
