package com.stackroute.contentanalysingservice.repository;

import com.stackroute.contentanalysingservice.model.Intent;
import com.stackroute.contentanalysingservice.model.Terms;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface TermsRepository extends Neo4jRepository<Terms, String> {
    //----------------------------------Nodes-------------------------------------//

    @Query("MATCH (c:Terms{name:$terms}), (p:Intent{name:$intent}) CREATE (c)-[r:indicator_of]->(p)")
    public void createRelation(String terms, String intent); //creating relation between Category and Keyword node

    @Query("create (p:Intent{name:$categoryName})")
    public void createParentNode(String categoryName);

    @Query("MATCH (n:Terms{name:$terms})-[:indicator_of]->(w:Intent) RETURN w.name")
    public String getCategoryNameBasedOnKeyword(String terms); // get name of parent node based on the name of child node

    @Query("match (n:Intent{name:$intentName})<-[:indicator_of]-(w:Terms) return w.name ")
    public List<String> getAllKeywordsBasedOnCategory(String intentName);

    @Query("create (c:Terms{name: $terms,parentName: $parent})")
    public void createKeywordNode(String terms, String parent);


    @Query("Match (p:Intent{name:$intent}) RETURN p")
    public Intent parentNodePresent(String intent);
}


