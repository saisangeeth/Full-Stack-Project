package com.stackroute.graphcommandservice.repository;

import com.stackroute.graphcommandservice.model.Video;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends Neo4jRepository<Video,String> {

    @Query("MATCH(a:Concept {conceptName: $concept}),(b:Video{url: $url}) CREATE(b)-[:video_of{intent:$intent,confidence:$confidence}]->(a)")
    public void relationBetweenVideoAndConcept(String concept,String url,String intent,int confidence);
}

