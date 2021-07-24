import { Component, OnInit,Inject } from '@angular/core';
import { Router } from '@angular/router';
import { FormControl, FormGroup } from '@angular/forms';
import { AuthenticateUserService } from '../service/authenticate-user.service';
import { User } from '../model/user';
import { MatSnackBar } from '@angular/material/snack-bar';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  duration:number;
  otp:any;
  logIn:FormGroup;
  userObject: User = new User();
  constructor(private authenticateUserService: AuthenticateUserService ,private router: Router,private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.logIn=new FormGroup({
      email: new FormControl(''),
      password: new FormControl('')
    })
  }

  onLogin()
  {
    console.log("login form value", this.logIn.value);
    this.userObject.emailId = this.logIn.value.email;
    this.userObject.password= this.logIn.value.password;
    this.authenticateUserService.performLogin(this.userObject).subscribe(response=>{
       localStorage.setItem('token',response['token']);
       localStorage.setItem('emailId',response['userName']);
       localStorage.setItem('userRole',response['userRole']);
       if(localStorage.getItem('userRole')=="SME"){
         console.log("logged in as sme");
         this.router.navigate(['/home/program'])
         this.showSnackbar('Logged in successfully ....','','1000');
       }
       else if(localStorage.getItem('userRole')=="TRAINEE"){
        console.log("logged in as trainee");
        this.router.navigate(['/home/program-dashboard'])
        this.showSnackbar('Logged in successfully ....','','1000');
       }
      console.log("Login successfull",response);
      this.logIn.reset();
    },error=>{
      this.showSnackbar('something went wrong','','1000');
      console.log('error while login',error)
    });

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

    onotpclick(){
      this.router.navigate(['/otpauth']);
    }
}





