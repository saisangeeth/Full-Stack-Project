package com.stackroute.graphqueryservice.respository;

import com.stackroute.graphqueryservice.model.*;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DomainQueryRepository extends Neo4jRepository<Domain, String> {

    @Query("MATCH (n:Domain{domainName:$domainName})<-[:concept_of]-(s:Concept) return s.conceptName")
    public List<String> getConceptRelatedToDomain(String domainName);

    @Query("MATCH (n:Concept{conceptName:$conceptName})<-[:concept_of]-(s:Concept) return s.conceptName")
    public List<String> getChildForConcept(String conceptName);

//    @Query("MATCH (n:Document)<-[:document_of]-(s:Concept{conceptName:$conceptName}) return n.conceptTitle")
//    public String getTitle(String conceptName);

    @Query("MATCH (n:Concept{conceptName:$conceptName})<-[:video_of]-(m:Video) return m")
    public List<Video> getDetailsOfVideo(String conceptName);

    @Query("MATCH (n:Domain{createdBy:$emailId}) return n")
    public List<Program> getAllDomainsBasedOnEmail(String emailId);

    @Query("MATCH (n:Domain)<-[:enrolled_to]-(w:UserNode{userEmail:$emailId}) return n")
    public List<Program> getAllCoursesBasedOnEmail(String emailId);


    @Query("MATCH (n:Domain) return n")
    public List<Program> getAllDomain();

    @Query("Match(n:Domain{createdBy:$createdBy}) return n")
    public List<Program> getAllDomain(String createdBy);

    //----------------------------- getting documents and video title and duration----------------------------------------//
    @Query("MATCH (n:Document)-[:document_of]->(m:Concept{conceptName:$conceptName}) return n.title ,n.durationInMinutes")
    public List<NodeTitleDuration> getAllDocumentsForGivenConcept(String conceptName);

    @Query("MATCH (n:Document)-[:document_of]->(m:Concept{conceptName:$conceptName}) return n.title")
    public List<String> getAllDocumentsTitleForGivenConcept(String conceptName);

    @Query("MATCH (n:Document)-[:document_of]->(m:Concept{conceptName:$conceptName}) return n.durationInMinutes")
    public List<String> getAllDocumentsDurationForGivenConcept(String conceptName);

    @Query("MATCH (n:Video)-[:video_of]->(m:Concept{conceptName:$conceptName}) return n.title ,n.durationInMinutes")
    public List<NodeTitleDuration> getAllVideoForGivenConcept(String conceptName);

    @Query("MATCH (n:Video)-[:video_of]->(m:Concept{conceptName:$conceptName}) return n.title")
    public List<String> getAllVideoTitleGivenConcept(String conceptName);

    @Query("MATCH (n:Video)-[:video_of]->(m:Concept{conceptName:$conceptName}) return n.durationInMinutes")
    public List<String> getAllVideoDurationGivenConcept(String conceptName);


    @Query("MATCH (n:Domain{domainName:$domainName})-[:IMAGE]->(m:Image) return m")
    public Image getImageForDomain(String domainName);

    @Query("MATCH (n:Concept{conceptName:$conceptName})<-[:document_of]-(w:Document{title:$title}) return w.url ")
    public String getUrlOfParticularContentDocumentTitle(String conceptName, String title);

    @Query("MATCH (n:Concept{conceptName:$conceptName})<-[:video_of]-(w:Video{title:$title}) return w.url ")
    public String getUrlOfParticularContentVideoTitle(String conceptName, String title);

    //*********************************Getting titles and duration for document and video**********************
//uncomment all the codes , commented only for testing purpose


//    @Query("MATCH (n:Document)-[:document_of]->(m:Concept{conceptName:$conceptName}) return n.title")
//    public List<String> getAllTitlesForDocumentOfConcepts(String conceptName);


    @Query("MATCH (n:Document)-[:document_of{intent:$intentName}]->(m:Concept{conceptName:$conceptName}) return n.title")
    public List<String> getAllTitlesForDocumentOfConcepts(String conceptName, String intentName);

    @Query("MATCH (n:Document{title:$title}) return n.durationInMinutes")
    public String getDurationForDocumentTitle(String title);

//    @Query("MATCH (n:Video)-[:video_of]->(m:Concept{conceptName:$conceptName}) return n.title")
//    public List<String> getAllTitlesForVideoOfConcepts(String conceptName);

    @Query("MATCH (n:Video)-[:video_of{intent:$intentName}]->(m:Concept{conceptName:$conceptName}) return n.title")
    public List<String> getAllTitlesForVideoOfConcepts(String conceptName, String intentName);

    @Query("MATCH (n:Video{title:$title}) return n.durationInMinutes")
    public String getDurationForVideoTitle(String title);


    @Query("MATCH (s:UserNode) return s.userEmail")
    public List<String> getAllEmails();

    @Query("MATCH (n:Concept{conceptName:$conceptName})<-[:document_of{intent:$intent}]-(s:Document) return s.url")
    public List<String> documentForParticularIntent(String conceptName, String intent);


    @Query("MATCH (n:Domain{domainName:$domainName}) return n")
    public Domain findDomainByDomainName(String domainName);


}
