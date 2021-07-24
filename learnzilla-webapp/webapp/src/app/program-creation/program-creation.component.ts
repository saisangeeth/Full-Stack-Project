import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Program } from '../model/program';
import { Router } from '@angular/router';
import { GraphCommandService } from '../service/graph-command.service';
import {ExcelsheetService} from '../service/excelsheet.service';
import domain from '../domain.json';

@Component({
  selector: 'app-program-creation',
  templateUrl: './program-creation.component.html',
  styleUrls: ['./program-creation.component.css']
})
export class ProgramCreationComponent implements OnInit {

  programCreation: FormGroup;
  selectedFile: File;
  programObject: Program = new Program();
  data: [][];
  files: any[] = [];
  imageFiles: any[] = [];
  uploadFiles: any[] = [];
  domains:string[]=domain;




  constructor(private graphCommandService: GraphCommandService, private router: Router, private excelService: ExcelsheetService) { }


  ngOnInit(): void {
    this.programCreation = new FormGroup({
      programTitle: new FormControl(''),
      description: new FormControl(''),
      domain: new FormControl(''),
      duration: new FormControl(''),
      file: new FormControl(''),
      uploadFile: new FormControl('')

    })
  }

  data1: any = [
    {
        "conceptName": "",
        "parent": "",
        "relationship": "concept-of",
    }
  ]

  exportAsXLSX():void {
    this.excelService.exportAsExcelFile(this.data1, 'program');
 }

  // onFileChange(evt: any) {
  //   const target : DataTransfer =  <DataTransfer>(evt.target);

  //   if (target.files.length !== 1) throw new Error('Cannot use multiple files');

  //   const reader: FileReader = new FileReader();

  //   reader.onload = (e: any) => {
  //     const bstr: string = e.target.result;

  //     const wb: XLSX.WorkBook = XLSX.read(bstr, { type: 'binary' });

  //     const wsname : string = wb.SheetNames[0];

  //     const ws: XLSX.WorkSheet = wb.Sheets[wsname];

  //     console.log(ws);

  //     this.data = (XLSX.utils.sheet_to_json(ws, { header: 1 }));

  //     console.log(this.data);

  //     let x = this.data.slice(1);
  //     console.log(x);

  //   };

  // reader.readAsBinaryString(target.files[0]);

  // }

  //Should go to program design page and take required data to the page as well
  goToDesignPage()
  {
    this.graphCommandService.programObject =
    {
      title: this.programCreation.value.programTitle,
      domain: this.programCreation.value.domain,
      description: this.programCreation.value.description,
      duration: this.programCreation.value.duration,
      image: this.imageFiles[0]
    }
    this.router.navigate(['/home/programdesign'])
  }


  createProgram() {
    console.log("program form value", this.programCreation.value);
    this.programObject.programTitle = this.programCreation.value.programTitle;
    this.programObject.domainName = this.programCreation.value.domain;
    this.programObject.duration = this.programCreation.value.duration;
    this.programObject.description = this.programCreation.value.description;
    this.programObject.createdBy = localStorage.getItem('emailId');
    this.programObject.file = this.files[0];
    this.uploadFiles.push(this.files[0]);
    this.uploadFiles.push(this.imageFiles[0]);
    this.graphCommandService.createNewProgram(this.uploadFiles, this.programObject).subscribe(response => {
      console.log("program created", response);
      this.programCreation.reset();
      this.files = [];
      this.uploadFiles=[];
      this.imageFiles=[];
      this.router.navigate(['/home/program']);
    }, error => {
      console.log('error while creating program', error);
    });
  }



  onFileChange(files) {
    this.prepareFilesList(files);
  }
  prepareFilesList(files: Array<any>) {
    for (const item of files) {
      item.progress = 0;
      this.files.push(item);
    }
    this.uploadFilesSimulator(0);
  }
  uploadFilesSimulator(index: number) {
    setTimeout(() => {
      if (index === this.files.length) {
        return;
      } else {
        const progressInterval = setInterval(() => {
          if (this.files[index].progress === 100) {
            clearInterval(progressInterval);
            this.uploadFilesSimulator(index + 1);
          } else {
            this.files[index].progress += 5;
          }
        }, 200);
      }
    }, 1000);
  }

  onFileDropped($event) {
    this.prepareFilesList($event);
  }

  formatBytes(bytes, decimals) {
    if (bytes === 0) {
      return '0 Bytes';
    }
    const k = 1024;
    const dm = decimals <= 0 ? 0 : decimals || 2;
    const sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];
    const i = Math.floor(Math.log(bytes) / Math.log(k));
    return parseFloat((bytes / Math.pow(k, i)).toFixed(dm)) + ' ' + sizes[i];
  }

  onImageFileChange(files) {
    this.prepareImageFilesList(files);
  }
  prepareImageFilesList(files: Array<any>) {
    for (const item of files) {
      item.progress = 0;
      this.imageFiles.push(item);
    }
    this.uploadImageFilesSimulator(0);
  }
  uploadImageFilesSimulator(index: number) {
    setTimeout(() => {
      if (index === this.imageFiles.length) {
        return;
      } else {
        const progressInterval = setInterval(() => {
          if (this.imageFiles[index].progress === 100) {
            clearInterval(progressInterval);
            this.uploadImageFilesSimulator(index + 1);
          } else {
            this.imageFiles[index].progress += 5;
          }
        }, 200);
      }
    }, 1000);
  }

  onImageFileDropped($event) {
    this.prepareImageFilesList($event);
  }

  restProgramForm(){
    this.programCreation.reset();
    this.files = [];
    this.uploadFiles=[];
    this.imageFiles=[];
  }


}



