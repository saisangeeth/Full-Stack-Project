package com.stackroute.graphcommandservice.repository;

import com.stackroute.graphcommandservice.model.Concept;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConceptRepository extends Neo4jRepository<Concept,String> {
    @Query("MATCH(a:Domain {domainName: $domain}),(b:Concept{conceptName: $conceptName}) CREATE(b)-[:concept_of]->(a)")
    public void domainConceptRelationship(String domain, String conceptName);

    @Query("MATCH(a:Concept {conceptName: $conceptParent}),(b:Concept{conceptName: $conceptName}) CREATE(b)-[:concept_of]->(a)")
    public void parentConceptRelationship(String conceptParent, String conceptName);

    @Query("Match(n:Concept{conceptName:$conceptName}) create (n)-[:hasDetails]->(e:Details{url:$url,time:$duration,intent:$intent})")
    public void addPropertyToConcept(String conceptName,String url,int duration,String intent);
}
