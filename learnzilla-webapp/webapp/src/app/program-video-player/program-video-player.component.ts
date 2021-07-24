import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EmbedVideoService } from 'ngx-embed-video';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { GraphQueryService } from '../service/graph-query.service';
import { MatDialog } from '@angular/material/dialog';
import { FeedbackDialogComponent } from '../feedback-dialog/feedback-dialog.component';
@Component({
  selector: 'app-program-video-player',
  templateUrl: './program-video-player.component.html',
  styleUrls: ['./program-video-player.component.css']
})
export class ProgramVideoPlayerComponent implements OnInit {
  iframe_html: any;
  youtubeUrl = "https://www.youtube.com/watch?v=ZZ_vnqvW4DQ";
  vid: any;
  htmlArray: any = "";
  videoUrl: string = "";
  urlSafe: SafeResourceUrl;



  @Input() documentObject;
  // documentObject="";
  // doc:any=this.documentObject.type;
  constructor(private embedService: EmbedVideoService, private graphQueryService: GraphQueryService,
    private activateRoute: ActivatedRoute,
    public sanitizer: DomSanitizer,public dialog: MatDialog) {
    this.iframe_html = this.embedService.embed(this.youtubeUrl);
  }

  ngOnInit(): void {
    // console.log("Viewer ngOnInit"+ this.documentObject, this.htmlArray);

    // this.getContent(this.documentObject.concept, this.documentObject.title);
  }

  // getContent (concept, title) {
  //   if(this.htmlArray == ""){
  //     console.log("Before doucment api call",title);
  //   this.graphQueryService.getUrl(concept, title).subscribe(response => {
  //     console.log("content url"+response.url);
  //     this.graphQueryService.getHtmlData(response.url).subscribe((result) => {
  //       this.htmlArray = result;
  //     }, error => {
  //       console.log('error', error);
  //     });
  //   }, error => {
  //     console.log('error', error);
  //   });}
  // }

  getYoutubeVideoUrl (concept, title) {
    if(this.videoUrl == ""){
    this.graphQueryService.getVideoUrl(concept, title).subscribe(response => {
      console.log("Video API call"+response.url.split("=")[1]);

      this.videoUrl = "https://www.youtube.com/embed/"+response.url.split("=")[1]+"?start=0";
      this.urlSafe= this.sanitizer.bypassSecurityTrustResourceUrl(this.videoUrl);
      console.log("video url = ",this.videoUrl);
    }, error => {
      console.log('error', error);
    });}
    }



    getUrlOfYoutubeVideo(videoUrl){
      return this.videoUrl;}









}
