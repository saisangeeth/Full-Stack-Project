package com.stackroute.graphqueryservice.controller;


import com.stackroute.graphqueryservice.model.*;
import com.stackroute.graphqueryservice.service.GraphService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @CrossOrigin
@Slf4j
@RestController
@RequestMapping("/api/v1")
@Api(value = "graphquery-service")
public class GraphController {
    GraphService graphService;

    @Autowired
    public GraphController(GraphService graphService) {
        this.graphService = graphService;
    }


    @GetMapping("/toc/{domainName}")
    public List<ConceptTitle> conceptUsingDomain(@PathVariable String domainName) {

        return graphService.getAllTheConcepts(domainName);

    }

    @GetMapping("/program/{email}")
    public List<Program> getAllPrograms(@PathVariable String email) {
        log.info(String.valueOf(graphService.getAllProgramById(email)));
        return graphService.getAllProgramById(email);

    }
    @GetMapping("/videourl/{conceptName}/{title}")
    public URL getVideoUrl(@PathVariable("conceptName") String conceptName, @PathVariable("title") String title ){
        return graphService.getUrlOfVideoTitle(conceptName,title);
    }


    @GetMapping("/video")
    public URL VideoUrl(@RequestParam("conceptName") String conceptName, @RequestParam("title") String title ){
        return graphService.getUrlOfVideoTitle(conceptName,title);
    }


    @GetMapping("/documenturl")
    public URL getDocumentUrl(@RequestParam("conceptName") String conceptName, @RequestParam("title") String title  ){
        return graphService.getUrlOfDocumentTitle(conceptName,title);
    }

//    @GetMapping("/test/{domainName}")
//    public List<Content> getTOC(@PathVariable("domainName") String domainName){
//        return graphService.getTOC(domainName);
//    }

    @GetMapping("/test/{domainName}/{intent}")
    public List<Content> getTOC(@PathVariable("domainName") String domainName,@PathVariable("intent") String intent){
        return graphService.getTOC(domainName,intent);
    }

    @GetMapping("/programs")
    public List<Program> getAllProgram(){
        return graphService.getAllTheProgram();
    }




    @GetMapping("/domain/{randomDomainName}")
    public List<String> conceptUsingRandomDomainName(@PathVariable String randomDomainName) {
        return graphService.getConceptOfAnyDomain(randomDomainName);
    }

    @GetMapping("/{userEmail}")
    public List<Program> getCoursesForTrainee(@PathVariable String userEmail) {
        return graphService.getCourseForUserEnrolledTO(userEmail);
    }

    @GetMapping("/getemails")
    public List<String> getEmailsForUSer(){
        return graphService.getEmails();
    }

    @GetMapping("/getdocument/{conceptName}/{intent}")
    public String getDocumentForConceptBasedOnIntent(@PathVariable("conceptName") String conceptName, @PathVariable("intent") String intent){
        return graphService.getDocumentForParticularConcept(conceptName,intent);
    }

    @GetMapping("concepts/{domainName}")
    public List<String> getConcepts(@PathVariable("domainName") String domainName){
        return graphService.getAllConceptsForParticularDomain(domainName);
    }

    @GetMapping("/domainname/{domainName}")
    public Program getDomainBasedOnDomainName(@PathVariable  String domainName){
        return graphService.getProgramInFromDomainName(domainName);
    }
}
