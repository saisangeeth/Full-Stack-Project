import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { GraphQueryService } from '../service/graph-query.service';

import { DomSanitizer, SafeUrl, SafeResourceUrl } from "@angular/platform-browser";
import { GraphCommandService } from '../service/graph-command.service';

import { MatDialog } from '@angular/material/dialog';
import { FeedbackDialogComponent } from '../feedback-dialog/feedback-dialog.component';
import moment from 'moment';
import { Program } from '../model/program';
@Component({
  selector: 'app-sidenav-program-content',
  templateUrl: './sidenav-program-content.component.html',
  styleUrls: ['./sidenav-program-content.component.css']
})

export class SidenavProgramContentComponent implements OnInit {




  @Output() getDocumentUrl = new EventEmitter();

  // @Output() getDomain = new EventEmitter();

  // sendDomain: any;
  programName: string;
  contentArray: any;
  tocArray: any;
  j: any = 44;
  panelOpenState = false;
  documentObject: any;
  htmlArray: any;
  url: SafeResourceUrl = "";
  intent: string;
  domain: string;
  urlSafe: SafeResourceUrl;
  videoUrl: string;
  loader = true;
  result: any = 70;
  loggedinUser: String;

  //for percentage

  public getCourseDuration = 0;

  public dateStart: any;
  public period: any;
  // public duration = 10;
  public duration: any;
  // public duration = this.getCourseDuration * 60 * 60;
  public percent = 0;
  public totalPeriod = 0;
  public time = 0;
  public totalTime = 0;

  program: Program = new Program();
  test: any;

  constructor(private graphQueryService: GraphQueryService,
    private activateRoute: ActivatedRoute, private sanitizer: DomSanitizer, public dialog: MatDialog, private router: Router, private graphCommandService: GraphCommandService) { }


  ngOnInit() {
    this.intent = this.activateRoute.snapshot.paramMap.get('intent');
    this.domain = this.activateRoute.snapshot.paramMap.get('domain');
    console.log("domain name is ", this.domain);
    this.programName = this.activateRoute.snapshot.paramMap.get('programname');
    this.duration = this.activateRoute.snapshot.paramMap.get('duration');

    // this.programObject = this.graphQueryService.programObject;
    this.loggedinUser = localStorage.getItem("emailId");
    console.log(this.loggedinUser);
    this.getCourseDuration = Number(this.duration) * 60 * 60;
    console.log("course duration ", this.getCourseDuration);
    // this.duration = this.getCourseDuration * 60 * 60;

    this.graphCommandService.getTime(this.loggedinUser, this.domain).subscribe(resp => {
      console.log(resp);
      this.time = resp;
      if ((Number(this.time)) <= this.getCourseDuration) {
        this.dateStart = moment();

        this.percent = (Number(this.time) / this.getCourseDuration) * 100;
        console.log(this.percent);
        //console.log(this.totalPeriod);
      }
      else {
        this.percent = 100;
      }
    })



    this.graphQueryService.getProgramTitles(this.intent, this.domain).subscribe(response => {
      console.log(' toc Array', response);
      this.tocArray = response;
      console.log("Title and concept", this.tocArray[0].parent.content.document.title, this.tocArray[0].parent.innerChild);
      this.getContent(this.tocArray[0].parent.innerChild, this.tocArray[0].parent.content.document.title);
      // this.documentObject = { title: this.tocArray[0].parent.content.document.title, concept: this.tocArray[0].parent.innerChild, type: "document" };
    }, error => {
      console.log('error', error);
    });

  }



  ngOnDestroy(): void {



    if ((Number(this.time)) <= this.getCourseDuration) {
      this.period = moment.utc(moment(this.dateStart).diff(moment(), 'seconds'));

      this.totalPeriod = (this.period * -1);
      this.totalTime = this.totalPeriod + this.time;


      this.graphCommandService.postTime(this.loggedinUser, this.domain, this.totalTime).subscribe(res => {
        console.log(res);
      })
      // localStorage.setItem('totalTime', (this.totalPeriod + Number(localStorage.getItem('totalTime'))).toString());

      //this.totalPeriod = this.totalPeriod + (this.period * -1);

      //console.log("period ", this.period)
      console.log("totalPeriod: ", this.totalTime);
      console.log("percent: ", this.percent, "totalTime: ", this.duration)
    }
  }
  resetTime() {
    localStorage.removeItem('totalTime');
  }

  getContent(concept, title) {
    // console.log("getContent Title" + title);
    this.graphQueryService.getUrl(concept, title).subscribe(response => {
      console.log("content url" + response.url);
      this.graphQueryService.getHtmlData(response.url).subscribe((result) => {
        console.log("Result is ", result);
        this.htmlArray = result;
        this.documentObject = { title: title, concept: concept, type: "document", result: result };
        console.log("*********Testing purpose*************");
        this.loader = false;
      }, error => {
        console.log('error', error);
      });
    }, error => {
      console.log('error', error);
    });
  }

  // getVideo(title, concept){
  //     console.log("getVideo()"+concept);
  //   this.documentObject = { title: title, concept: concept, type: "video" };
  //   // this.getDocumentUrl.emit(object);
  // }



  getVideo(title, concept) {

    this.graphQueryService.getVideoLinks(concept, title).subscribe(response => {
      console.log("Video API call", response.url.split("=")[1]);

      this.videoUrl = "https://www.youtube.com/embed/" + response.url.split("=")[1] + "?start=120";
      this.urlSafe = this.sanitizer.bypassSecurityTrustResourceUrl(this.videoUrl);
      this.documentObject = { title: title, concept: concept, type: "video", url: this.urlSafe };
      console.log("video url = ", this.videoUrl);
    }, error => {
      console.log('error', error);
    });
  }

  getApiData(concept, title) {

    this.graphQueryService.getUrl(concept, title).subscribe(response => {
      console.log(concept);
      console.log(title);
      console.log(response);
      // this. tocArray = response;
    }, error => {
      console.log('error', error);
    });
  }






  openDialog() {
    const dialogRef = this.dialog.open(FeedbackDialogComponent,{
      data:{
        domain: this.domain
      }}
      );

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

  goToCertificate() {
    this.router.navigate(['/home/program-certificate', this.programName]);
  }

}


