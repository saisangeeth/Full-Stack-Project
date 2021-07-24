import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GraphQueryService } from '../service/graph-query.service';

@Component({
  selector: 'app-program-container',
  templateUrl: './program-container.component.html',
  styleUrls: ['./program-container.component.css']
})
export class ProgramContainerComponent implements OnInit {

  isLoading = true;
  programArray: any;
  retrievedImage: any;

  constructor(private graphQueryService : GraphQueryService, private router: Router) { }
  ngOnInit(): void {
    this.graphQueryService.getAllPrograms().subscribe(response => {
      this.isLoading = false;
      console.log('program array', response);
      this.programArray = response;
    }, error => {
      console.log('error', error);
    })
  }

  createCousre(){
    this.router.navigate(['/home/programcreation']);
  }


  // ngOnInit(): void {
  //   this.graphQueryService.getProgramsByEmailId().subscribe(response => {
  //     console.log('program array', response);
  //     this.programArray = response;

  //   }, error => {
  //     console.log('error', error);
  //   })
  // }

}
