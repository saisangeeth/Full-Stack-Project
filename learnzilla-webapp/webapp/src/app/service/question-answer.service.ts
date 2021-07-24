import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError } from "rxjs/operators";
import { throwError } from 'rxjs';
import { Question } from '../model/question';
import { Answer } from '../model/answer';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class QuestionAnswerService {
  apiBaseUrl=environment.apiBaseUrl;
  localUrl= "http://localhost:8080";

  constructor(private httpClient:HttpClient) { }

  public createNewQuestion( questionObject:Question) {


    return this.httpClient.post<Question>(this.apiBaseUrl+"/questionanswer-service/api/v1/question", questionObject
    )
      .pipe(catchError(this.errorHandler));
  }

  public createNewAnswer(questionTitle:string,answerObject:Answer) {

    return this.httpClient.post<Answer>(this.apiBaseUrl+"/questionanswer-service/api/v1/answer/"+ questionTitle, answerObject
    )
      .pipe(catchError(this.errorHandler));
  }

  public getAllContentQuestions(contentTitle:string) {

    return this.httpClient.get<any>(this.apiBaseUrl+"/questionanswer-service/api/v1/questions/"+ contentTitle
    )
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
