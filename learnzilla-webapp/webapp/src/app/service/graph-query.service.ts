import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError } from "rxjs/operators";
import { throwError } from 'rxjs';
import { DomSanitizer,SafeUrl,SafeResourceUrl } from "@angular/platform-browser";
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class GraphQueryService {
  apiBaseUrl=environment.apiBaseUrl;
  localUrl= "http://localhost:8080";
  getUrls: any;
  programObject: any;

  constructor(private httpClient: HttpClient,private sanitizer: DomSanitizer) { }

  public getAllProgramDetails() {
    return this.httpClient.get<any>(this.apiBaseUrl+"/graphquery-service/api/v1/programs")
      .pipe(catchError(this.errorHandler));
  }

  public getAllPrograms() {
    let email = localStorage.getItem('emailId');
    return this.httpClient.get<any>(this.apiBaseUrl+"/graphquery-service/api/v1/program/"+email)
      .pipe(catchError(this.errorHandler));
  }

  public getProgramsForTrainee() {
    let email = localStorage.getItem('emailId');
    return this.httpClient.get<any>(this.apiBaseUrl+"/graphquery-service/api/v1/"+email)
      .pipe(catchError(this.errorHandler));
  }



  public getProgramTitles(intent,domain) {
    return this.httpClient.get<any>(this.apiBaseUrl+"/graphquery-service/api/v1/test/"+domain+"/"+intent)
      .pipe(catchError(this.errorHandler));
  }

  public getUrl(concept,title) {
    return this.httpClient.get<any>(this.apiBaseUrl+"/graphquery-service/api/v1/documenturl",{
      params:{
        conceptName: concept,
        title: title
      }})
    .pipe(catchError(this.errorHandler));
  }

  public getVideoUrl(concept,title) {
    return this.httpClient.get<any>(this.apiBaseUrl+"/graphquery-service/api/v1/videourl"+"/"+concept+"/"+title)
    .pipe(catchError(this.errorHandler));
  }

  public getVideoLinks(concept,title) {
    return this.httpClient.get<any>(this.apiBaseUrl+"/graphquery-service/api/v1/video",{
      params:{
        conceptName: concept,
        title: title
      }
    })
    .pipe(catchError(this.errorHandler));
  }

  httpOptionsPlain:any = {
    headers: new HttpHeaders({
      'Accept': 'text/plain',
      'Content-Type': 'text/plain'
    }),
    'responseType': 'text'
  };
  // https://api.scrapingdog.com/scrape?api_key=60defa88ec759112fea4b7c5&url=
  public getHtmlData(url) {

    return this.httpClient.get<any>("https://api.scraperapi.com/?api_key=18a0a47f516fce7dd59dc680547dea2d&url="+url ,this.httpOptionsPlain)
    // this.httpClient.get(urlll, {responseType: 'text'})
    .pipe(catchError(this.errorHandler));

  }






  public getHtmlDataJsoup(url) {

    return this.httpClient.get<any>(this.apiBaseUrl+"/graphquery-service/api/v1/htmlcontent?url="+url ,this.httpOptionsPlain)
    // this.httpClient.get(urlll, {responseType: 'text'})
    .pipe(catchError(this.errorHandler));

  }




  public getAllEnrolledProgram(){
    return this.httpClient.get<any>(this.apiBaseUrl+'/graphquery-service/api/v1/programs')
      .pipe(catchError(this.errorHandler));
  }

  public getProgramsByEmailId () {
    let email = localStorage.getItem('emailId');
    console.log(email);
    return this.httpClient.get<any>(this.apiBaseUrl+'/graphquery-service/api/v1/program/' + email)
      .pipe(catchError(this.errorHandler));
  }

  public getAllRegisteredEmailId() {
    return this.httpClient.get<any>(this.apiBaseUrl+"/graphquery-service/api/v1/getemails")
      .pipe(catchError(this.errorHandler));
  }

  public getProgramFromDomainName(domainName){
    return this.httpClient.get<any>(this.apiBaseUrl+"/graphquery-service/api/v1/domainname/"+domainName)
      .pipe(catchError(this.errorHandler));
  }

  public getConceptsFromDomainName(domainName){
    return this.httpClient.get<any>(this.apiBaseUrl+"/graphquery-service/api/v1/concepts/"+domainName)
      .pipe(catchError(this.errorHandler));
  }

  public errorHandler(error: Response | any) {
    if (error instanceof ErrorEvent) {
      console.error("an error occured:", error.message);
      return throwError("something bad happened");
    }
    else {
      console.error(`backend returned code ${error.status},` +
        `body was:${error.message}`);
      return throwError(error);
    }
  }


}
