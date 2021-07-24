import { Injectable } from '@angular/core';
import { User } from '../model/user';
import { HttpClient } from '@angular/common/http';
import { catchError } from "rxjs/operators";
import { throwError } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthenticateUserService
{
  apiBaseUrl=environment.apiBaseUrl;
  localUrl= "http://localhost:8080";

  constructor(private httpClient:HttpClient) { }

  performLogin(userDetail:User) {

    return this.httpClient.post<User>(this.apiBaseUrl+"/authentication-service/api/v1/login", userDetail)
      .pipe(catchError(this.errorHandler));

  }

  public sendOtpEmail(emailId: string) {
    return this.httpClient.get<any>(this.apiBaseUrl+"/authentication-service/api/v1/email/" + emailId)
  }

  public authenticateOtp(otpCode: string,emailId:string) {

      return this.httpClient.get<any>(this.apiBaseUrl+"/authentication-service/api/v1/otp/" + otpCode +"/"+emailId)
      .pipe(catchError(this.errorHandler))
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
