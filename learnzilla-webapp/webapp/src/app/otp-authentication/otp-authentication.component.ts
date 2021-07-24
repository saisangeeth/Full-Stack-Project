import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormControl, FormGroup } from '@angular/forms';
import { AuthenticateUserService } from '../service/authenticate-user.service';
import { User } from '../model/user';
import { OtpAuthenticationDialogueBoxComponent } from '../otp-authentication-dialogue-box/otp-authentication-dialogue-box.component';
import { MatDialog } from '@angular/material/dialog';



@Component({
  // selector: 'app-otp-authentication',
  templateUrl: './otp-authentication.component.html',
  styleUrls: ['./otp-authentication.component.css']
})
export class OtpAuthenticationComponent implements OnInit {

  emailRequest:FormGroup;
   userObject: User = new User();
  // otpRequest:FormGroup;
  //userObject: User = new User();
  code: '';
  otp: any;
  constructor(private authenticateService:AuthenticateUserService,private router: Router,public dialog: MatDialog) { }

  ngOnInit(): void {
    // this.otpRequest=new FormGroup({
    //   OTP: new FormControl(''),
    // })

    this.emailRequest=new FormGroup({
           email: new FormControl(''),
        })
  }



  onemailSubmit()
    {
      console.log("login form value", this.emailRequest.value);
      this.userObject.emailId = this.emailRequest.value.email;
      this.authenticateService.sendOtpEmail(this.userObject.emailId).subscribe(response=>{
        //  localStorage.setItem('token',response['token']);
        //  localStorage.setItem('emailId',response);
        console.log("Authentication code sent successfull",response);
        // this.router.navigate(['/otpMatcher']);
        this.emailRequest.reset();
        this.openDialog();
      },error=>{
        if(error.status == 200){
          this.openDialog();
        }
        console.log('error while login',error)
      });

    }

    openOtpSubmission(){
      this.router.navigate(['/otpMatcher']);
    }

    openDialog(): void {
      const dialogRef = this.dialog.open(OtpAuthenticationDialogueBoxComponent, {
        width: '500px',
        data: {emailId: this.userObject.emailId}
      });

      dialogRef.afterClosed().subscribe(result => {
        console.log('The dialog was closed');
        this.otp = result;
      });
    }

    onClick()
    {
      this.router.navigate(['']);
    }





}



