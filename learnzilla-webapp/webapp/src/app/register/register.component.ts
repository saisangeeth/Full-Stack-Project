import { Component, OnInit } from '@angular/core';
import { FormControl,FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { UserProfile } from '../model/user-profile';
import { UserRole } from '../model/user-role';
import { UserProfileService } from '../service/user-profile.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerUser: FormGroup;
  userProfileObject: UserProfile = new UserProfile();
  data:any;

  constructor(private userProfileService:UserProfileService,private router:Router,private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.registerUser=new FormGroup({
      emailId: new FormControl(''),
      password: new FormControl('')
    })
  }

  onRegister(){
    console.log("registration form value", this.registerUser.value);
    this.userProfileObject.emailId = this.registerUser.value.emailId;
    this.userProfileObject.password= this.registerUser.value.password;
    this.userProfileObject.userRole= UserRole.TRAINEE;
    this.userProfileService.performRegistration(this.userProfileObject).subscribe(response=>{
this.data=response;

      console.log("registered successfully",response);
      this.showSnackbar('Registered successfully ....','','1000');
      this.router.navigate(['/login']);
      this.registerUser.reset();
    },error=>{
      this.showSnackbar('something went wrong','','1000');
      console.log('error while login',error);
   
    });

  }

  openLogin(){
    this.router.navigate(['/login']);
  }

  showSnackbar(content,action,duration) {
    this._snackBar.open(content,action,duration);
    let sb = this._snackBar.open(content, action,{
      duration: duration,
      panelClass: ["custom-style"]
    });
    sb.onAction().subscribe(() => {
      sb.dismiss();
    });
    }

    onClick()
    {
      this.router.navigate(['']);
    }

}
