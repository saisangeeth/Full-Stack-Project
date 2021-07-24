package com.stackroute.graphcommandservice.repository;

import com.stackroute.graphcommandservice.model.Document;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends Neo4jRepository<Document,String> {
    @Query("MATCH(a:Concept {conceptName: $concept}),(b:Document{title: $title}) CREATE(b)-[:document_of{intent:$intent,confidence:$confidence}]->(a)")
    public void relationBetweenDocumentAndComponent(String concept,String title,String intent,int confidence);

}
