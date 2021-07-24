import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError } from "rxjs/operators";
import { throwError } from 'rxjs';
import { Program } from '../model/program';
import { UserEnrollment } from '../model/user-enrollment';
import { Ratings } from '../model/ratings';
import { Time } from '../model/time';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class GraphCommandService {
  apiBaseUrl=environment.apiBaseUrl;
  localUrl= "http://localhost:8080";

  public programObject: any;

  constructor(private httpClient: HttpClient) { }

  public createNewProgram(file: File[], programDetail: Program) {
    console.log(file);
    const data: FormData = new FormData();
    data.append('file', file[0]);
    data.append('file', file[1]);
    data.append('details', JSON.stringify(programDetail));
    return this.httpClient.post<Program>(this.apiBaseUrl+"/graphcommand-service/api/v1/program", data, {
      headers: { responseType: 'text' }
    })
      .pipe(catchError(this.errorHandler));
  }

  public createProgramUsingDesign(file: File, programDetail: Program, conceptArray: any) {
    console.log(file);
    const data: FormData = new FormData();
    data.append('file', file);
    data.append('concept', JSON.stringify(conceptArray));
    data.append('details', JSON.stringify(programDetail));
    return this.httpClient.post<Program>(this.apiBaseUrl+"/graphcommand-service/api/v1/program-design", data, {
      headers: { responseType: 'text' }
    })
      .pipe(catchError(this.errorHandler));
  }

  public enrollUserToProgram(userObject: UserEnrollment) {
    return this.httpClient.post<UserEnrollment>(this.apiBaseUrl+"/graphcommand-service/api/v1/user", userObject
    )
      .pipe(catchError(this.errorHandler));
  }

  public sendFeedback(rating: Ratings) {

    return this.httpClient.post<Ratings>(this.apiBaseUrl+'/graphcommand-service/api/v1/ratings', rating)
    // this.httpClient.get(urlll, {responseType: 'text'})
    .pipe(catchError(this.errorHandler));

  }



  public getTime(email,domain) {

    return this.httpClient.get<any>(this.apiBaseUrl+'/graphcommand-service/api/v1/gettime/' + email + '/' + domain)
      .pipe(catchError(this.errorHandler));
  }


  public postTime(loggedinUser,domain,totalTime) {

    return this.httpClient.post<any>(this.apiBaseUrl+"/graphcommand-service/api/v1/time/"+ loggedinUser + "/" + totalTime, domain)
    // http://localhost:8080/graphcommand-service/api/v1/time/{email}/{domain}/{time}"
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
