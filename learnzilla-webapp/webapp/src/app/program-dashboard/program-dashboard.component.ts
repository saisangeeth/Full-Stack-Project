import { Component, OnInit } from '@angular/core';
import { User } from '../model/user';
import { GraphQueryService } from '../service/graph-query.service';

@Component({
  selector: 'app-program-dashboard',
  templateUrl: './program-dashboard.component.html',
  styleUrls: ['./program-dashboard.component.css']
})
export class ProgramDashboardComponent implements OnInit {

  programArray: any;
  retrievedImage: any;
  user: User = new User();
  isLoading = true;

  constructor(private graphQueryService : GraphQueryService) { }
  ngOnInit(): void {
    this.graphQueryService.getProgramsForTrainee().subscribe(response => {
      console.log('program array', response);
      this.programArray = response;
      this.isLoading = false;
    }, error => {
      console.log('error', error);
    });
  }


}
