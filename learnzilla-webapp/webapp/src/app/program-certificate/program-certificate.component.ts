import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import jspdf from "jspdf";
import html2canvas from 'html2canvas';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-program-certificate',
  templateUrl: './program-certificate.component.html',
  styleUrls: ['./program-certificate.component.css']
})
export class ProgramCertificateComponent implements OnInit {
  userName: string;
  programName: string;

  // @ViewChild('certificate',{static: false}) certificate!:ElementRef<any>;

  constructor(private activateRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.userName = localStorage.getItem("emailId").split("@")[0].toUpperCase();
    this.programName = this.activateRoute.snapshot.paramMap.get('programname');
    console.log("program name",this.programName);
  }

  download(){
    // let pdf=new jsPDF('p','pt','a4');
    // pdf.html(this.certificate.nativeElement,{
    //   callback: (pdf)=>{
    //     pdf.save("certificate.pdf");
    //   }
    // });
    let element=document .getElementById('certificate');
    html2canvas(element).then((canvas)=>{
      let imgData = canvas.toDataURL('image/png');
      let doc = new jspdf();
      let imgHeight = canvas.height * 230 / canvas.width;
      doc.addImage(imgData,0,0,230,imgHeight);
      doc.save("certificate.pdf");
    })
  }
}
