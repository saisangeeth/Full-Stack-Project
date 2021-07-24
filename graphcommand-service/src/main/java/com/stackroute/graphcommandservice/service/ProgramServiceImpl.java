package com.stackroute.graphcommandservice.service;

import com.stackroute.graphcommandservice.model.*;
import com.stackroute.graphcommandservice.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProgramServiceImpl implements ProgramService {

    DomainRepository domainRepository;
    DocumentRepository documentRepository;
    ConceptRepository conceptRepository;
    VideoRepository videoRepository;
    UserRepository userRepository;

    @Autowired
    public ProgramServiceImpl(DomainRepository domainRepository, ConceptRepository conceptRepository, DocumentRepository documentRepository, VideoRepository videoRepository, UserRepository userRepository) {
        this.domainRepository = domainRepository;
        this.conceptRepository = conceptRepository;
        this.documentRepository = documentRepository;
        this.videoRepository = videoRepository;
        this.userRepository = userRepository;
    }

    List<Response> consumerArray = new ArrayList<>();

    @RabbitListener(queues = "${spring.rabbitmq.queue2}")
    public void consumerFunction(Response response) {
        Domain domain = domainRepository.findById(response.getDomainName()).get(); //retrieving the domain from the domainName
        domain.setStatus(Status.RUNNING);//changing the status of the current status
        domainRepository.setStatus(domain.getDomainName(),"RUNNING");
        System.out.println("*********************"+domain);
        if (response.getType().equals("page")) {
            createDocumentNode(response);
        } else {
            createVideoNode(response);
        }
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue3}")
    public void consumer(String userEmail) {
        createUser(userEmail);
    }


    @Override
    public Program createConceptGraph(Program program, List<ExcelSheetData> excelArray) {
        Optional<Domain> domainExist = domainRepository.findById(program.getDomainName());
        if (!domainExist.isPresent()) {
            Domain domain = new Domain(program.getDomainName(), program.getCreatedBy(), program.getProgramTitle(), program.getDescription(), program.getDuration(), program.getImageName(), program.getImageType(), program.getPicByte(), 3, Status.STARTED);
            domainRepository.save(domain);
            for (ExcelSheetData data : excelArray) {
                Optional<Concept> conceptExist = conceptRepository.findById(data.getParent());

                if (conceptExist.isPresent()) {
                    Concept concept = new Concept(data.getConceptName());
                    conceptRepository.save(concept);
                    conceptRepository.parentConceptRelationship(data.getParent(), data.getConceptName());
                } else {
                    Optional<Domain> domainObject = domainRepository.findById(data.getParent());
                    if (domainObject.isPresent()) {
                        Concept concept = new Concept(data.getConceptName());
                        conceptRepository.save(concept);
                        conceptRepository.domainConceptRelationship(data.getParent(), data.getConceptName());
                    } else {
                        Concept concept = new Concept(data.getParent());
                        conceptRepository.save(concept);
                        conceptRepository.parentConceptRelationship(data.getParent(), data.getConceptName());
                    }
                }
            }
        }
        return program;
    }
//
//    public void addDetailsToTheNode(){
//        for(Response response:consumerArray){
//            if(conceptRepository.existsById(response.getConceptName())){
//                conceptRepository.addPropertyToConcept(response.getConceptName(),response.getUrl(), response.getReadingTime(), response.getIntent() );
//            }
//        }
//    }

    public void createDocumentNode(Response response) {
        Document document = new Document(response.getTitle(), response.getUrl(), response.getDuration(), response.getNoOfVideos(), response.getNoOfImages());
        documentRepository.save(document);
        documentRepository.relationBetweenDocumentAndComponent(response.getConceptName(), document.getTitle(), response.getIntent(), response.getConfidenceScore());
    }


    public void createVideoNode(Response response) {
        Video video = new Video(response.getUrl(), response.getTitle(), response.getDuration(), response.getNoOfVideos(), response.getNoOfImages());
        videoRepository.save(video);
        videoRepository.relationBetweenVideoAndConcept(response.getConceptName(), response.getUrl(), response.getIntent(), response.getConfidenceScore());
    }

//    public void addDetailsToTheNode() {
//        if (conceptRepository.existsById(response.getConceptName())) {
//            conceptRepository.addPropertyToConcept(response.getConceptName(), response.getUrl(), response.getReadingTime(), response.getIntent());
//        }
//    }


    @Override
    public UserNode createUser(String email) {
        UserNode newUserNode = new UserNode(email);
        userRepository.save(newUserNode);
        return newUserNode;
    }

    @Override
    public void addUserToCourse(UserEnrollment userEnrollment) {
        List<String> emails = userEnrollment.userEmails;
        for (String email : emails) {
            userRepository.createRelationBetweenDomainAndUser(userEnrollment.getDomainName(), email);
        }
    }

    @Override
    public void addRatingByTheUser(String domain, String userEmail, int rating, String description) {
        int sum = 0, avg = 0, counter = 0;
        List<Integer> ratings = new ArrayList<>();
        try {
            userRepository.addRatingAndDescriptionToTheUserNode(domain, userEmail, rating, description);
            ratings = userRepository.getRatingsForParticularDomain(domain);
            log.info(ratings.toString());
            for (int rate : ratings) {
                if (rate > 0) {
                    sum += rate;
                    counter++;
                }

            }
            avg = (int)(sum / counter);
            userRepository.setRatingToTheParticularDomain(domain, avg);
        } catch (Exception exception) {
            userRepository.addRatingAndDescriptionToTheUserNode(domain, userEmail, rating, description);
            ratings.add(rating);
            userRepository.setRatingToTheParticularDomain(domain, rating);
        }
    }

    @Override
    public List<String> getProgramsUserIsEnrolledTo(String userEmail) {
        return userRepository.getAllDomainUserIsEnrolledTo(userEmail);
    }

    @Override
    public Program createConceptTreeFromArray(Program program, List<ArrayTree> arrayTree) {
        Domain domain = new Domain(program.getDomainName(), program.getCreatedBy(), program.getProgramTitle(),
                program.getDescription(), program.getDuration(), program.getImageName(), program.getImageType(), program.getPicByte(), 3, Status.STARTED);
        domainRepository.save(domain);

        for (ArrayTree parentConcept : arrayTree) {
            conceptRepository.save(new Concept(parentConcept.getParentConcept()));
            conceptRepository.domainConceptRelationship(domain.getDomainName(), parentConcept.getParentConcept());
            if (arrayTree.size() > 0) {
                for (ChildConcepts childConcepts : parentConcept.getChildConcepts()) {
                    conceptRepository.save(new Concept(childConcepts.getChildConcept()));
                    conceptRepository.parentConceptRelationship(parentConcept.getParentConcept(), childConcepts.getChildConcept());
                }
            }
        }
        return program;
    }

    @Override
    public void setTimeForUser(String email, String videoTitle, long time) {
        if (userRepository.checkTimeRelationPresent(email, videoTitle)) {
            userRepository.updateTime(email, videoTitle, time);
        } else {
            userRepository.setTimeForUser(email, videoTitle, time);
        }
    }

    @Override
    public Long getTimeForUser(String email, String domain) {
        return userRepository.getTimeForUser(email, domain);
    }

    @Override
    public void setTimeForProgram(String userEmail, String domainName, long time) {
        userRepository.setTimeForCompleteProgram(userEmail, domainName, time);
    }

    @Override
    public Long getTimeForProgram(String email, String domainName) {
        try {
            return userRepository.getTimeForCompleteProgram(email, domainName);
        } catch (Exception e) {
            return Long.parseLong("0");
        }
    }


}






