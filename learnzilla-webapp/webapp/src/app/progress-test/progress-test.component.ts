import { Component, OnInit, OnDestroy } from '@angular/core';
import moment from "moment";

@Component({
  selector: 'app-progress-test',
  templateUrl: './progress-test.component.html',
  styleUrls: ['./progress-test.component.css']
})
export class ProgressTestComponent implements OnInit, OnDestroy {

  public dateStart: any;
  public period: any;
  public duration = 10;
  public percent = 0;
  public totalPeriod = 0;


  constructor() { }

  ngOnInit(): void {

    if((Number(localStorage.getItem('totalTime')))<=this.duration){
    this.dateStart = moment();
    
    this.percent = (Number(localStorage.getItem('totalTime')) / this.duration) * 100;      

    //console.log(this.totalPeriod);
    }
    else{
      this.percent=100;
    }
  }

  ngOnDestroy(): void {

    if((Number(localStorage.getItem('totalTime')))<=this.duration){
    this.period = moment.utc(moment(this.dateStart).diff(moment(), 'seconds'));

    this.totalPeriod = (this.period * -1);

    localStorage.setItem('totalTime', (this.totalPeriod + Number(localStorage.getItem('totalTime'))).toString());
    
    //this.totalPeriod = this.totalPeriod + (this.period * -1);

    //console.log("period ", this.period)
    console.log("totalPeriod: ", localStorage.getItem('totalTime'));
    console.log("percent: ", this.percent, "totalTime: ", this.duration)
    }
  }

  resetTime()
  {
    localStorage.removeItem('totalTime');
  }

}
