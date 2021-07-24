import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {FormControl,FormGroup} from '@angular/forms';
import {MatAutocompleteSelectedEvent} from '@angular/material/autocomplete';
import {MatChipInputEvent} from '@angular/material/chips';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import {ContentData  } from '../model/content-data';
import { ContentSearchService } from '../service/content-search.service';
import { ActivatedRoute, Router } from '@angular/router';
import { GraphQueryService } from '../service/graph-query.service';


@Component({
  selector: 'app-content-generation',
  templateUrl: './content-generation.component.html',
  styleUrls: ['./content-generation.component.css']
})
export class ContentGenerationComponent implements OnInit {

  contentGenerationForm : FormGroup;

  visible: boolean = true;
  selectable: boolean = true;
  removable: boolean = true;
  addOnBlur: boolean = false;
  contentObject:ContentData=new ContentData();
  title:string;
  domain:string;
  isLoading=false;


  separatorKeysCodes = [ENTER, COMMA];

  conceptCtrl = new FormControl();

  filteredConcepts: Observable<any[]>;

  concepts = [];

  allConcepts = [];

  @ViewChild('conceptInput') conceptInput: ElementRef;

  constructor(private contentSearchService: ContentSearchService, private activateRoute:ActivatedRoute,private router:Router, private graphQueryService : GraphQueryService) {

  }

  ngOnInit(): void {
    this. contentGenerationForm = new FormGroup({
      programName : new FormControl(''),
      domainName : new FormControl(''),
      conceptCtrl : new FormControl()
    })
    this.title=this.activateRoute.snapshot.paramMap.get('title');
    this.domain=this.activateRoute.snapshot.paramMap.get('domain');
    this.contentGenerationForm.get("programName").setValue(this.title);
    this.contentGenerationForm.get("programName").disable();
    this.contentGenerationForm.get("domainName").setValue(this.domain);
    this.contentGenerationForm.get("domainName").disable();
    this.graphQueryService.getConceptsFromDomainName(this.domain).subscribe(response => {
      console.log('program', response);
      this.concepts = response;
      this.allConcepts=response;
    }, error => {
      console.log('error', error);
    })
    this.filteredConcepts = this.conceptCtrl.valueChanges.pipe(
        startWith(null),
        map((concept: string | null) => concept ? this.filter(concept) : this.allConcepts.slice()));
    console.log("title value", this.title);
  }

  add(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;


    if ((value || '').trim()) {
      this.concepts.push(value.trim());
    }


    if (input) {
      input.value = '';
    }

    this.conceptCtrl.setValue(null);
  }

  remove(fruit: any): void {
    const index = this.concepts.indexOf(fruit);

    if (index >= 0) {
      this.concepts.splice(index, 1);
    }
  }

  filter(name: string) {
    return this.allConcepts.filter(fruit =>
        fruit.toLowerCase().indexOf(name.toLowerCase()) === 0);
  }

  selected(event: MatAutocompleteSelectedEvent): void {
    this.concepts.push(event.option.viewValue);
    this.conceptInput.nativeElement.value = '';
    this.conceptCtrl.setValue(null);
  }

  generateContent(conceptInput:any){
    this.isLoading=true;
    this.contentGenerationForm.get("conceptCtrl").setValue(this.concepts);
    console.log("form value",this.contentGenerationForm.value);
    this.contentObject.programName = this.title;
    this.contentObject.domainName = this.domain;
    this.contentObject.conceptNames = this.contentGenerationForm.value.conceptCtrl;
    this.contentSearchService.createContentProgram(this.contentObject).subscribe(response => {
      console.log("program created",response);
      this.isLoading=false;
      this.contentGenerationForm.get("conceptCtrl").reset();
      this.concepts=[];
      this.router.navigate(['/home/program']);
    }, error => {
      console.log("error while creating program",error);
      this.isLoading=false;
    })
    // setTimeout(()=>{
    //   this.isLoading=false;
    // },5000);
  }

}
