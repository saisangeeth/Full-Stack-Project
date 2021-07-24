import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  showFeature : Boolean;
  loggedinUser:String;
  constructor(private router: Router) { }

  ngOnInit(): void {
    this.loggedinUser = localStorage.getItem("emailId").split("@")[0];
   // this.loggedinUser="kajal";
    if(localStorage.getItem('userRole') == "SME"){
        this.showFeature=true;
    }else if(localStorage.getItem('userRole') == "TRAINEE"){
      this.showFeature=false;
    }
    }

  openLogin(){
    this.router.navigate(['/login']);
  }

  openRegister(){
    this.router.navigate(['/register']);
  }

  // loggedin()
  // { 
  //   localStorage.getItem('token');  
  //   this.loggedinUser = localStorage.getItem("emailId").split("@")[0];
  //   return this.loggedinUser;
  // }

  onLogout()
  {
    
    localStorage.removeItem('token');
    localStorage.removeItem('emailId');
    localStorage.removeItem('userRole');
    this.router.navigate(['']);
  }

}










