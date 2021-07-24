import { ThisReceiver } from '@angular/compiler';
import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormArray, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { Program } from '../model/program';
import { GraphCommandService } from '../service/graph-command.service';

@Component({
  selector: 'app-program-design',
  templateUrl: './program-design.component.html',
  styleUrls: ['./program-design.component.css']
})
export class ProgramDesignComponent implements OnInit {

  conceptForm: FormGroup;
  private isButtonVisible = false;
  index = 0;
  program: any;
  imageURL: any;
  programObject: Program = new Program();
  uploadFiles: File;


  constructor(private fb: FormBuilder, private graphCommandService: GraphCommandService,private router: Router) {}

  ngOnInit() {
    this.conceptForm = this.fb.group({
     concepts: this.fb.array([])
    });
    this.concepts().push(this.newConcept());
    this.childConcepts(this.index).push(this.newChildConcept());

    this.program = this.graphCommandService.programObject;

    this.imageMaker();
  }

  imageMaker()
  {
    var reader = new FileReader();

		reader.onload = (event: any) => {
			this.imageURL = event.target.result;
		}

    reader.readAsDataURL(this.program.image);
  }

  concepts(): FormArray {
    return this.conceptForm.get('concepts') as FormArray;
  }

  newConcept(): FormGroup {
    return this.fb.group({
     parentConcept: '',
      childConcepts: this.fb.array([])
    });
  }

  addConcept() {
    this.concepts().push(this.newConcept());
    this.index++;
    this.childConcepts(this.index).push(this.newChildConcept());

    if(this.index != 0)
    {
      this.isButtonVisible = true;
    }
    else
    {
      this.isButtonVisible = false;
    }

    console.log(this.index);
  }

  removeConcept(conceptIndex: number) {
      this.concepts().removeAt(conceptIndex);
      this.index--;

      if(this.index != 0)
    {
      this.isButtonVisible = true;
    }
    else
    {
      this.isButtonVisible = false;
    }

      console.log(this.index);
  }

  childConcepts(conceptIndex: number): FormArray {
    return this.concepts()
      .at(conceptIndex)
      .get('childConcepts') as FormArray;
  }

  newChildConcept(): FormGroup {
    return this.fb.group({
      childConcept: ''
    });
  }

  addChildConcept(conceptIndex: number) {
    this.childConcepts(conceptIndex).push(this.newChildConcept());
  }

  removeChildConcept(conceptIndex: number, childConceptIndex: number) {
    this.childConcepts(conceptIndex).removeAt(childConceptIndex);
  }

  onSubmit()
  {
    console.log("submitted");
    console.log(this.conceptForm.value);

    this.programObject.programTitle = this.program.title;
    this.programObject.domainName = this.program.domain;
    this.programObject.duration = this.program.duration;
    this.programObject.description = this.program.description;
    this.programObject.createdBy = localStorage.getItem('emailId');
    this.uploadFiles = this.program.image;
    console.log(this.programObject);
    this.graphCommandService.createProgramUsingDesign(this.uploadFiles, this.programObject, this.conceptForm.value).subscribe(response => {
      console.log("program created", response);
      this.conceptForm.reset();
      this.router.navigate(['/home/program']);

    }, error => {
      console.log('error while creating program', error);
    });
  }
}
