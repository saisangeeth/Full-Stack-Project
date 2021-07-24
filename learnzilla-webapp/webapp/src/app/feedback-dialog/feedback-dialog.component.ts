import { Inject } from '@angular/core';
import { Component, Input, OnInit } from '@angular/core';
import {FormBuilder, FormControl,FormGroup} from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { Program } from '../model/program';
import { Ratings } from '../model/ratings';
import { GraphCommandService } from '../service/graph-command.service';
import { GraphQueryService } from '../service/graph-query.service';
@Component({
  selector: 'app-feedback-dialog',
  templateUrl: './feedback-dialog.component.html',
  styleUrls: ['./feedback-dialog.component.css']
})
export class FeedbackDialogComponent implements OnInit {
  domain: any;
  feedbackForm : FormGroup;
  currentRate=0;
  feedback:String;
  program: Program = new Program();
  rating: Ratings = new Ratings();
  programObject: any;




  loggedinUser:String;
  constructor(private graphQueryService: GraphQueryService,
    private activateRoute: ActivatedRoute,private graphCommandService: GraphCommandService,
    @Inject(MAT_DIALOG_DATA) public recievedData:any) {
      this.domain = recievedData.domain;
     }

  ngOnInit(): void {
    // this.domain = this.activateRoute.snapshot.paramMap.get('domain');
    this.loggedinUser = localStorage.getItem("emailId")
    console.log(this.loggedinUser);
    // this.programObject = this.graphQueryService.programObject;
    this.feedbackForm = new FormGroup(
      {
        feedback: new FormControl('')
      }
    );

  }



  onSubmit() {
    console.log(this.feedbackForm.value.feedback);
    console.log(this.currentRate);
    console.log(this.loggedinUser);

    this.rating.domain = this.domain;
    this.rating.userEmail = this.loggedinUser;
    this.rating.rating = this.currentRate;
    this.rating.description = this.feedbackForm.value.feedback;

    console.log('Rating  ' + this.rating.description,this.rating.domain,this.rating.rating,this.rating.userEmail);
    this.graphCommandService.sendFeedback(this.rating).subscribe(response => {
      console.log(response);

    }, error => {
      console.log('error', error);
    });
  }







  // send(feedback){

  //   console.log(this.feedback);
  //   // console.log(this.feedbackForm.feedback.value);
  //   console.log(this.currentRate);
  //   console.log(this.loggedinUser);
  //   this.graphQueryService.sendFeedback().subscribe(response => {
  //     console.log(response);

  //   }, error => {
  //     console.log('error', error);
  //   });
  // }
}
