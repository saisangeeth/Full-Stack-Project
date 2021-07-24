package com.stackroute.graphcommandservice.repository;

import com.stackroute.graphcommandservice.model.UserNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface UserRepository extends Neo4jRepository<UserNode,String> {


    @Query("match (n:Domain{domainName:$domainName}) return n.createdBy")
    public String getNameOfSmeWhoCreatedTheCourse(String domainName);

    @Query("MATCH (u:UserNode{userEmail:$userEmail}),(d:Domain{domainName:$domain}) CREATE (u)-[:enrolled_to{rating:0}]->(d)")
    public void createRelationBetweenDomainAndUser(String domain, String userEmail);

    @Query("MATCH (:UserNode{userEmail:$userEmail})-[w:enrolled_to]->(:Domain{domainName:$domain}) set w.rating=$rating,w.description=$description")
    public void addRatingAndDescriptionToTheUserNode(String domain ,String userEmail,int rating,String description);

    @Query("match (w:UserNode{userEmail:$userEmail}) ,(s:Video{title:$videoTitle}) create (w)-[:watch_time{time:$time}]->(s)")
    public void setTimeForUser(String userEmail,String videoTitle,long time);

    @Query("match (w:UserNode{userEmail:$userEmail})-[r:watch_time]->(s:Video{title:$videoTitle}) set r.time = $time")
    public void updateTime(String userEmail,String videoTitle,long time);

    @Query("MATCH  (p:UserNode{userEmail:$userEmail}),(s:Video{title:$videoTitle}) RETURN EXISTS( (p)-[:watch_time]->(s) )")
    public Boolean checkTimeRelationPresent(String userEmail,String videoTitle);

    @Query("MATCH (n:UserNode{userEmail:$email})-[w:watch_time]->(:Video{title:$videoTitle}) return w.time")
    public Long getTimeForUser(String email,String videoTitle);

    @Query("MATCH (:Domain{domainName:$domainName})<-[w:enrolled_to]-(:UserNode) return avg(w.rating) as w")
    public int getAverageRatingToGivenDomain(String domainName);

    @Query("MATCH (s:Domain{domainName:$domainName}) return s.rating")
    public int getTheCompleteDomain(String domainName);



    @Query("MATCH (w:Domain)<-[:enrolled_to]-(:UserNode{userEmail:$userEmail}) return w.domainName")
    public List<String> getAllDomainUserIsEnrolledTo(String userEmail);


    //testing it out

    @Query("MATCH (n:Domain{domainName:$domainName})<-[r:enrolled_to]-(:UserNode) return r.rating")
    public List<Integer> getRatingsForParticularDomain(String domainName);

    @Query("MATCH  (w:Domain{domainName:$domainName}) set w.rating=$rating")
    public void setRatingToTheParticularDomain(String domainName, int rating);

    @Query("MATCH (:UserNode{userEmail:$userEmail})-[w:enrolled_to]->(:Domain{domainName:$domain}) set w.time=$time")
    public void setTimeForCompleteProgram(String userEmail,String domain,long time);

    @Query("MATCH (n:UserNode{userEmail:$email})-[w:enrolled_to]->(:Domain{domainName:$domain}) return w.time")
    public Long getTimeForCompleteProgram(String email,String domain);

}
