import { HttpClient} from '@angular/common/http';
import { Input } from '@angular/core';
import { Component, OnInit } from '@angular/core';import { FormControl, FormGroup } from '@angular/forms';
''
import { UploadResult, MdEditorOption } from "ngx-markdown-editor";
import { Answer } from '../model/answer';
import { Question } from '../model/question';
import { QuestionAnswerService } from '../service/question-answer.service';


@Component({
  selector: 'app-question-answer-section',
  templateUrl: './question-answer-section.component.html',
  styleUrls: ['./question-answer-section.component.css']
})
export class QuestionAnswerSectionComponent implements OnInit {
  [x: string]: any;
  maxChars = 100;
  desc = '';
  chars = 0;
  @Input() public title: string;

  questionPublisher:FormGroup;
  questionObject:Question = new Question();

  commentAnswer:FormGroup;
  answerObject:Answer = new Answer();

  questionData:any;
  questionArray:any;
  currentlyOpenedItemIndex: any;
  panelOpenState = false;


  public options: MdEditorOption = {
    showPreviewPanel: false,
    enablePreviewContentClick: false,
    resizable: true,
    customRender: {
      image: function(href: string, title: string, text: string) {
        let out = `<img style="max-width: 100%; border: 20px solid red;" src="${href}" alt="${text}"`;
        if (title) {
          out += ` title="${title}"`;
        }
        out += (<any>this.options).xhtml ? "/>" : ">";
        return out;
      }
    }
  };

  public content: string;
  public mode: string = "editor";

  constructor(private httpClient: HttpClient,private questionAnswerService:QuestionAnswerService) {

    this.preRender = this.preRender.bind(this);
    this.postRender = this.postRender.bind(this);
  }


  ngOnInit(): void {

    this.questionPublisher=new FormGroup({
      questionTitle:new FormControl(''),
      questionBody:new FormControl('')

    })

    this.commentAnswer=new FormGroup({
     answerBody:new FormControl('')
    })


    // this.httpClient.get<any>("http://localhost:3000/java").subscribe((data)=>
    //   this.questionData = data)
    this.getAllQuestions();
  }

  getAllQuestions(){
    this.questionAnswerService.getAllContentQuestions(this.title).subscribe(response=>{
      console.log('all question',response);
      this.questionArray=response;
    },error =>{
      console.log('error while displaying quesions',error);
    });
  }

  togglePreviewPanel() {
    this.options.showPreviewPanel = !this.options.showPreviewPanel;
    this.options = Object.assign({}, this.options);
  }

  changeMode() {
    if (this.mode === "editor") {
      this.mode = "preview";
    } else {
      this.mode = "editor";
    }
  }

  togglePreviewClick() {
    this.options.enablePreviewContentClick = !this.options
      .enablePreviewContentClick;
    this.options = Object.assign({}, this.options);
  }

  toggleResizeAble() {
    this.options.resizable = !this.options.resizable;
    this.options = Object.assign({}, this.options);
  }

  doUpload(files: Array<File>): Promise<Array<UploadResult>> {
    return new Promise((resolve, reject) => {
      setTimeout(() => {
        let result: Array<UploadResult> = [];
        for (let file of files) {
          result.push({
            name: file.name,
            url: `https://avatars3.githubusercontent.com/${file.name}`,
            isImg: file.type.indexOf("image") !== -1
          });
        }
        resolve(result);
      }, 3000);
    });
  }

  onEditorLoaded(editor) {
    console.log(`ACE Editor Ins: `, editor);

  }

  preRender(mdContent) {
    console.log(`preRender fired`);
    return mdContent;
  }

  postRender(html) {
    console.log(`postRender fired`);
    // return '<h1>Test</h1>';
    return html;
  }

  onPreviewDomChanged(dom: HTMLElement) {
    console.log(dom);
    console.log(dom.innerHTML);
  }

  createQuestion()
  {
     console.log("question publish value",this.questionPublisher.value, this.content);
     console.log("Question Title ",this.questionPublisher.value.questionTitle);
     console.log("Question Body ",this.questionPublisher.value.questionBody);
     this.questionObject.questionTitle = this.questionPublisher.value.questionTitle;
     this.questionObject.questionBody = this.content;
     this.questionObject.publishedBy = localStorage.getItem('emailId');
     this.questionObject.contentTitle = this.title;
    //  this.questionObject.questionDate= localStorage.getItem('date');
     this.questionAnswerService.createNewQuestion(this.questionObject).subscribe(response=>{
       console.log("question published",response);
       this.questionPublisher.reset();
       this.getAllQuestions();
     },error => {
       console.log('error while publishing question',error);
     }
     );

  }

  createAnswer(questionTitle)
  {
    console.log(" commented answer value ",this.commentAnswer.value.answerBody);
    this.answerObject.answerBody = this.commentAnswer.value.answerBody;
    this.answerObject.answeredBy = localStorage.getItem('emailId');
    // this.answerObject.answerDate = localStorage.getItem('date');
    this.questionAnswerService.createNewAnswer(questionTitle,this.answerObject).subscribe(response=>{
      console.log("commented answer",response);
      this.commentAnswer.reset();
      this.getAllQuestions();
      this.content = "";
    },
    error =>{
      console.log('error while commenting answer',error);
    });


  }

}
