import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { COMMA, ENTER } from '@angular/cdk/keycodes';
import { FormControl, FormGroup } from '@angular/forms';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { MatChipInputEvent } from '@angular/material/chips';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { ActivatedRoute, Router } from '@angular/router';
import { Program } from '../model/program';

import { GraphQueryService } from '../service/graph-query.service';
import { GraphCommandService } from '../service/graph-command.service';
import { UserEnrollment } from '../model/user-enrollment';


@Component({
  selector: 'app-enroll-trainee',
  templateUrl: './enroll-trainee.component.html',
  styleUrls: ['./enroll-trainee.component.css'],
})
export class EnrollTraineeComponent implements OnInit {
  traineeEnrollmentForm: FormGroup;

  visible: boolean = true;
  selectable: boolean = true;
  removable: boolean = true;
  addOnBlur: boolean = false;
  title: string;
  program: Program = new Program();
  domain:string;
  userEnrollment: UserEnrollment = new UserEnrollment();

  isLoading = false;

  separatorKeysCodes = [ENTER, COMMA];

  nameCtrl = new FormControl();
  programObject: any;

  filteredNames = [];

  names = [];

  allNames = [
    'parsibhargavi23@gmail.com',
    'kajal@gmail.com',
    'ankit@gmail.com',
  ];

  @ViewChild('nameInput') nameInput: ElementRef;

  constructor(private activateRoute: ActivatedRoute, private router: Router, private graphQueryService: GraphQueryService,
    private graphCommandService: GraphCommandService) {
    // this.filteredNames = this.nameCtrl.valueChanges.pipe(
    //   startWith(null),
    //   map((name: string | null) =>
    //     name ? this.filter(name) : this.allNames.slice()
    //   )
    // );
  }

  ngOnInit(): void {
    this.domain=this.activateRoute.snapshot.paramMap.get('domain');
    this.traineeEnrollmentForm = new FormGroup({
      programName: new FormControl(''),
      nameCtrl: new FormControl(),
    });
    this.programObject = this.graphQueryService.programObject;
    this.graphQueryService.getAllRegisteredEmailId().subscribe((data)=>{
      this.filteredNames = data;
    }, error => {
      console.log('error while getting emails', error);
    });

  }

  add(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;

    if ((value || '').trim()) {
      this.names.push(value.trim());
    }

    if (input) {
      input.value = '';
    }

    this.nameCtrl.setValue(null);
  }

  remove(fruit: any): void {
    const index = this.names.indexOf(fruit);

    if (index >= 0) {
      this.names.splice(index, 1);
    }
  }

  filter(name: string) {
    return this.allNames.filter(
      (fruit) => fruit.toLowerCase().indexOf(name.toLowerCase()) === 0
    );
  }

  selected(event: MatAutocompleteSelectedEvent): void {
    this.names.push(event.option.viewValue);
    this.nameInput.nativeElement.value = '';
    this.nameCtrl.setValue(null);
  }

  enrollTrainee() {

    this.traineeEnrollmentForm.get('nameCtrl').setValue(this.names);
    console.log('form value', this.traineeEnrollmentForm.value);
    console.log('names', this.names);
    this.userEnrollment.domainName = this.domain;
    console.log("domain Name", this.userEnrollment.domainName);
    this.userEnrollment.userEmails = this.names;
    this.graphCommandService.enrollUserToProgram(this.userEnrollment).subscribe(response => {
      console.log("enrolling user ", response);
      this.traineeEnrollmentForm.reset();
      this.names = [];
    }, error => {
      console.log('error while enrolling participants', error);
    });
  }
}
