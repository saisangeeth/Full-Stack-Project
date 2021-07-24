import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError } from "rxjs/operators";
import { throwError } from 'rxjs';
import {ContentData  } from '../model/content-data';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ContentSearchService {
  apiBaseUrl=environment.apiBaseUrl;
  localUrl= "http://localhost:8080";

  constructor(private httpClient:HttpClient) { }

  public createContentProgram(contentData:ContentData) {
    return this.httpClient.post<ContentData>(this.apiBaseUrl+"/contentsearch-service/api/v1/content", contentData)
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
