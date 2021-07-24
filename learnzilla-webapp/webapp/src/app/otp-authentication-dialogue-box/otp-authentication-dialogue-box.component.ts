import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { User } from '../model/user';
import { AuthenticateUserService } from '../service/authenticate-user.service';

// export interface DialogData {
//   otp:any;
// }

@Component({
  selector: 'app-otp-authentication-dialogue-box',
  templateUrl: './otp-authentication-dialogue-box.component.html',
  styleUrls: ['./otp-authentication-dialogue-box.component.css']
})
export class OtpAuthenticationDialogueBoxComponent implements OnInit {

  // constructor( public dialogRef: MatDialogRef<OtpAuthenticationDialogueBoxComponent>,
  //   @Inject(MAT_DIALOG_DATA) public data: DialogData) { }

  userObject: User = new User();
  otpRequest:FormGroup;
  //userObject: User = new User();
  code: '';
  otp: any;
  emailId:any;

  constructor( public dialogRef: MatDialogRef<OtpAuthenticationDialogueBoxComponent>,private authenticateService:AuthenticateUserService,private router: Router
   ,@Inject(MAT_DIALOG_DATA) public recievedData:any) {
     this.emailId=recievedData.emailId;
    }

  ngOnInit(): void {
    this.otpRequest=new FormGroup({
      OTP: new FormControl(''),
    })
  }


  onSubmit()
  {
    console.log("otp form value", this.otpRequest.value);
    this.code = this.otpRequest.value.OTP;
    console.log("received code",this.code);
    this.authenticateService.authenticateOtp(this.code,this.emailId).subscribe(response=>{
       localStorage.setItem('token',response['token']);
       localStorage.setItem('emailId',response['userName']);
       localStorage.setItem('userRole',response['userRole']);
       this.dialogRef.close();
       if(localStorage.getItem('userRole')=="SME"){
         console.log("logged in as sme");
         this.router.navigate(['/home/program'])
       }
       else if(localStorage.getItem('userRole')=="TRAINEE"){
        console.log("logged in as trainee");
        this.router.navigate(['/home/program-dashboard'])
       }
      console.log("Authentication code recieved successfull",response);
     // this.router.navigate(['/home/program-dashboard']);
      this.otpRequest.reset();
    },error=>{
      console.log('error while login',error)
    });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  // openOtpSubmission(){
  //   this.router.navigate(['/otpauth']);
  // }


}
