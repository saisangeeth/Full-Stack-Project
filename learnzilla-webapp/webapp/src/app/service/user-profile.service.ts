import { Injectable } from '@angular/core';
import { UserProfile } from '../model/user-profile';
import { HttpClient } from '@angular/common/http';
import { catchError } from "rxjs/operators";
import { throwError } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserProfileService {
  apiBaseUrl=environment.apiBaseUrl;
  localUrl= "http://localhost:8080";

  constructor(private httpClient:HttpClient) { }

  performRegistration(userProfileDetails: UserProfile)
  {
    return this.httpClient.post<UserProfile>(this.apiBaseUrl+"/userprofile-service/api/v1/user", userProfileDetails)
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
