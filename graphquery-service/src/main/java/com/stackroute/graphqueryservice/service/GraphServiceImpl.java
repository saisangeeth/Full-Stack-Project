package com.stackroute.graphqueryservice.service;

import com.stackroute.graphqueryservice.model.*;
import com.stackroute.graphqueryservice.respository.DomainQueryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class GraphServiceImpl implements GraphService {
    DomainQueryRepository domainQueryRepository;


    @Autowired
    public GraphServiceImpl(DomainQueryRepository domainQueryRepository) {
        this.domainQueryRepository = domainQueryRepository;
    }

    @Override
    public List<ConceptTitle> getAllTheConcepts(String domainName) {
        ConceptTitle conceptTitle;
        List<ConceptTitle> conceptTitles = new ArrayList<>();
        List<String> conceptOfDomain = domainQueryRepository.getConceptRelatedToDomain(domainName);
        for (String concept : conceptOfDomain) {
            List<String> childConcept = domainQueryRepository.getChildForConcept(concept);
            conceptTitle = new ConceptTitle(concept, childConcept);
            conceptTitles.add(conceptTitle);
        }
        return conceptTitles;
    }

    @Override
    public List<String> getConceptOfAnyDomain(String domainName) {
        List<String> conceptNames = domainQueryRepository.getConceptRelatedToDomain(domainName);
        List<String> childNode = new ArrayList<>();
        List<String> subChild;
        List<Video> detailsOfVideo = new ArrayList<>();
        for (String concept : conceptNames) {
            childNode.add(concept);
            subChild = domainQueryRepository.getChildForConcept(concept);
            childNode.addAll(subChild);
            for (String conceptDetail : childNode) {
                detailsOfVideo.addAll(domainQueryRepository.getDetailsOfVideo(conceptDetail));
            }
        }
        log.info("Executed Successfully");
        log.info(String.valueOf(childNode));
        return childNode;
    }

    @Override
    public List<Program> getAllProgramById(String email) {

        return Collections.unmodifiableList(domainQueryRepository.getAllDomainsBasedOnEmail(email));
    }

    @Override
    public List<Program> getCourseForUserEnrolledTO(String email) {
        return Collections.unmodifiableList(domainQueryRepository.getAllCoursesBasedOnEmail(email));
    }

//Uncomment this is working code commented only for testing purpose

//    @Override
//    public List<Content> getTOC(String domainName) {
//
//        List<Content> finalContent = new ArrayList<>();// final return statement
//        List<String> parentConceptList = new ArrayList<>(domainQueryRepository.getConceptRelatedToDomain(domainName));
//        List<String> innerConcept;
//
//
//        try {
//            for (String concept : parentConceptList) {
//                //getting title and duration for conceptName
////                List<NodeTitleDuration> videoForConcept = domainQueryRepository.getAllVideoForGivenConcept(concept);
////                List<NodeTitleDuration> documentForConcept = domainQueryRepository.getAllDocumentsForGivenConcept(concept);
//                if (domainQueryRepository.getAllTitlesForDocumentOfConcepts(concept).isEmpty()) {
//                    continue;
//                }
//                List<String> parentDocument = domainQueryRepository.getAllTitlesForDocumentOfConcepts(concept);
//                List<String> parentVideo = domainQueryRepository.getAllTitlesForVideoOfConcepts(concept);
//
//                //adding the video and document duration and tile for the parent concept
//                HashMap<String, ContentDetails> mapResources = new HashMap<>();
//                mapResources.put("video", new ContentDetails(parentVideo.get(0), domainQueryRepository.getDurationForVideoTitle(parentVideo.get(0))));
//                mapResources.put("document", new ContentDetails(parentDocument.get(0), domainQueryRepository.getDurationForDocumentTitle(parentDocument.get(0))));
//                Details parentDetails = new Details(concept, mapResources);
//                //getting all the child of the already present concept
//                innerConcept = domainQueryRepository.getChildForConcept(concept);
//                List<Details> childConceptDetails = new ArrayList<>();
//                if (innerConcept.isEmpty()) {
//                    childConceptDetails.add(new Details());
//                    Content finalDetails = new Content(parentDetails, childConceptDetails);
//                    finalContent.add(finalDetails);
//
//                } else {
//                    //for adding all the details of the child concept
//
//                    for (String childConcept : innerConcept) {
//                        //getting all the titles and duration for the child concept
//                        if (domainQueryRepository.getAllTitlesForDocumentOfConcepts(childConcept).isEmpty()) {
//                            continue;
//                        }
//
//                        List<String> childDocument = domainQueryRepository.getAllTitlesForDocumentOfConcepts(childConcept);
//                        List<String> childVideo = domainQueryRepository.getAllTitlesForVideoOfConcepts(childConcept);
//
//                        //adding the video and document details for the given concept
//
//                        HashMap<String, ContentDetails> childMapResources = new HashMap<>();
//
//                        ContentDetails videoDetails = new ContentDetails(childVideo.get(0), domainQueryRepository.getDurationForVideoTitle(childVideo.get(0)));
//                        childMapResources.put("video", videoDetails);
//                        ContentDetails documentDetails = new ContentDetails(childDocument.get(0), domainQueryRepository.getDurationForDocumentTitle(childDocument.get(0)));
//                        childMapResources.put("document", documentDetails);
//
//                        Details childDetails = new Details(childConcept, childMapResources);
//
//                        childConceptDetails.add(childDetails);
//                    }
//                    //storing it the concept list array which is to displayed finally
//                    Content finalDetails = new Content(parentDetails, childConceptDetails);
//                    finalContent.add(finalDetails);
//
//                }
//            }
//            return finalContent;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return finalContent;
//    }


    @Override
    public List<Content> getTOC(String domainName, String intent) {

        List<Content> finalContent = new ArrayList<>();// final return statement
        List<String> parentConceptList = new ArrayList<>(domainQueryRepository.getConceptRelatedToDomain(domainName));
        List<String> innerConcept;


        try {
            for (String concept : parentConceptList) {
                //getting title and duration for conceptName
//                List<NodeTitleDuration> videoForConcept = domainQueryRepository.getAllVideoForGivenConcept(concept);
//                List<NodeTitleDuration> documentForConcept = domainQueryRepository.getAllDocumentsForGivenConcept(concept);


                //commented for testing purpose

//                if (((domainQueryRepository.getAllTitlesForDocumentOfConcepts(concept, intent)).isEmpty()) || ((domainQueryRepository.getAllTitlesForVideoOfConcepts(concept, intent)).isEmpty())) {
//                    continue;
//                }

                if (((domainQueryRepository.getAllTitlesForDocumentOfConcepts(concept, intent)).isEmpty())) {
                    continue;
                }

                List<String> parentDocument = domainQueryRepository.getAllTitlesForDocumentOfConcepts(concept, intent);
                List<String> parentVideo = domainQueryRepository.getAllTitlesForVideoOfConcepts(concept, "BEGINNER");

                //adding the video and document duration and tile for the parent concept
                HashMap<String, ContentDetails> mapResources = new HashMap<>();
                ContentDetails videoDetailsParent;
                if (parentVideo.size() == 0) {
                    videoDetailsParent = new ContentDetails();
                } else {
                    videoDetailsParent = new ContentDetails(parentVideo.get(0), domainQueryRepository.getDurationForVideoTitle(parentVideo.get(0)));
                }
                mapResources.put("video", videoDetailsParent);

                mapResources.put("document", new ContentDetails(parentDocument.get(0), domainQueryRepository.getDurationForDocumentTitle(parentDocument.get(0))));
                Details parentDetails = new Details(concept, mapResources);
                //getting all the child of the already present concept
                innerConcept = domainQueryRepository.getChildForConcept(concept);
                List<Details> childConceptDetails = new ArrayList<>();
                if (innerConcept.isEmpty()) {
                    childConceptDetails.add(new Details());
                    Content finalDetails = new Content(parentDetails, childConceptDetails);
                    finalContent.add(finalDetails);
                } else {
                    //for adding all the details of the child concept

                    for (String childConcept : innerConcept) {
                        //getting all the titles and duration for the child concept
                        if (domainQueryRepository.getAllTitlesForDocumentOfConcepts(childConcept, intent).isEmpty()) {
                            //testing purpose only ,can delete
                            childConceptDetails.add(new Details());
                            continue;
                        }

                        List<String> childDocument = domainQueryRepository.getAllTitlesForDocumentOfConcepts(childConcept, intent);
                        List<String> childVideo = domainQueryRepository.getAllTitlesForVideoOfConcepts(childConcept, "BEGINNER");

                        //adding the video and document details for the given concept

                        HashMap<String, ContentDetails> childMapResources = new HashMap<>();
                        ContentDetails videoDetails;
                        if (childVideo.size() == 0) {
                            videoDetails = new ContentDetails();
                        } else {
                            videoDetails = new ContentDetails(childVideo.get(0), domainQueryRepository.getDurationForVideoTitle(childVideo.get(0)));
                        }
                        childMapResources.put("video", videoDetails);
                        ContentDetails documentDetails = new ContentDetails(childDocument.get(0), domainQueryRepository.getDurationForDocumentTitle(childDocument.get(0)));
                        childMapResources.put("document", documentDetails);

                        Details childDetails = new Details(childConcept, childMapResources);

                        childConceptDetails.add(childDetails);
                    }
                    //storing it the concept list array which is to displayed finally
                    Content finalDetails = new Content(parentDetails, childConceptDetails);
                    finalContent.add(finalDetails);

                }
            }
            return finalContent;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return finalContent;
    }

    @Override
    public URL getUrlOfVideoTitle(String conceptName, String videoTitle) {
        return new URL(domainQueryRepository.getUrlOfParticularContentVideoTitle(conceptName, videoTitle));
    }

    @Override
    public URL getUrlOfDocumentTitle(String conceptName, String documentTitle) {
        return new URL(domainQueryRepository.getUrlOfParticularContentDocumentTitle(conceptName, documentTitle));
    }

    @Override
    public List<Program> getAllTheProgram() {
        return domainQueryRepository.getAllDomain();
    }

    @Override
    public List<String> getEmails() {
        return domainQueryRepository.getAllEmails();
    }

    @Override
    public String getDocumentForParticularConcept(String conceptName, String intent) {
        return domainQueryRepository.documentForParticularIntent(conceptName, intent).get(0);
    }

    @Override
    public List<String> getAllConceptsForParticularDomain(String domainName) {
        List<String> directChild = domainQueryRepository.getConceptRelatedToDomain(domainName);
        List<String> finalList = new ArrayList<>();
        for (String parent : directChild) {
            finalList.add(parent);
            List<String> child = domainQueryRepository.getChildForConcept(parent);
            if (child.isEmpty()) {
                continue;
            } else {
                finalList.addAll(child);
            }
        }
        return finalList;

    }


    @Override
    public Program getProgramInFromDomainName(String domainName) {
        Domain domain =  domainQueryRepository.findDomainByDomainName(domainName);
        Program program = new Program(domain.getCreatedBy(),domain.getProgramTitle(),domain.getDomainName(),domain.getDuration(),domain.getDescription(),
                domain.getImageName(),domain.getImageType(),domain.getPicByte(),domain.getRating(),domain.getStatus());
        return program;

    }
}





