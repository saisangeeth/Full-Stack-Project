import { Component,ElementRef, ViewChild, OnInit,Input } from '@angular/core';
import { COMMA, ENTER } from '@angular/cdk/keycodes';
import { FormControl, FormGroup } from '@angular/forms';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { MatChipInputEvent } from '@angular/material/chips';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { ActivatedRoute, Router } from '@angular/router';
import { GraphQueryService } from '../service/graph-query.service';



@Component({
  selector: 'app-program-details',
  templateUrl: './program-details.component.html',
  styleUrls: ['./program-details.component.css']
})
export class ProgramDetailsComponent implements OnInit {

    currentRate:string = "4";
    @Input() public domain:string
    program:any;
    retrievedImage: any;



    constructor(private graphQueryService : GraphQueryService, private router: Router) {

    }

    ngOnInit(): void {
      this.graphQueryService.getProgramFromDomainName(this.domain).subscribe(response => {
      console.log('program', response);
      this.program = response;
      this.retrievedImage = 'data:image/jpeg;base64,' + this.program.picByte;
    }, error => {
      console.log('error', error);
    })

    }

}



