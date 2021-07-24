package com.stackroute.contentanalysingservice.service;

import com.stackroute.contentanalysingservice.model.Intent;
import com.stackroute.contentanalysingservice.model.Terms;
import com.stackroute.contentanalysingservice.repository.TermsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NodeCreationServiceImpl implements NodeCreationService {

    @Autowired
    TermsRepository termsRepository;

    // Added all the keywords with which we will be able to identify the category of the page
    final String[] BEGINNER = new String[]{"easy", "dummies", "simple", "starter"};
    final String[] INTERMEDIATE = new String[]{"average", "moderate", "mediocre", "standard"};
    final String[] EXPERT = new String[]{"Qualified", "Experience", "Professional", "Trained"};
    final Terms[] terms = new Terms[4];

    @Override
    public List<Terms> getAllKeywords() {
        return termsRepository.findAll();
    }

    @Override
    public void createAndRelateNodes(Intent intent) {

        if (termsRepository.parentNodePresent(intent.getName()) == null) {
            termsRepository.createParentNode(intent.getName());
            if ((intent.getName()).equals("BEGINNER")) {

                for (int i = 0; i < 4; i++) {
                    terms[i] = new Terms(BEGINNER[i], "BEGINNER");
                    extracted(terms[i]);
                }
            } else if ((intent.getName()).equals("EXPERT")) {

                for (int i = 0; i < 4; i++) {
                    terms[i] = new Terms(EXPERT[i], "EXPERT");
                    extracted(terms[i]);
                }
            }
            if (intent.getName().equals("INTERMEDIATE")) {
                for (int i = 0; i < 4; i++) {
                    terms[i] = new Terms(INTERMEDIATE[i], "INTERMEDIATE");
                    extracted(terms[i]);
                }
            }
        }
    }

    private void extracted(Terms terms) {
        termsRepository.createKeywordNode(terms.getName(), terms.getParentName());
        termsRepository.createRelation(terms.getName(), terms.getParentName());
    }
}
