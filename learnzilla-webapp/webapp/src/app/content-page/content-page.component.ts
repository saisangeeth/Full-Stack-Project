import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-content-page',
  templateUrl: './content-page.component.html',
  styleUrls: ['./content-page.component.css']
})
export class ContentPageComponent implements OnInit {


  lessons: string[] = [ 'Content for Lesson 1: Lorem ipsum dolor sit amet consectetur adipisicing elit. Saepe animi, tempora quibusdam pariatur, neque atque natus veritatis dolore culpa tempore voluptate aspernatur perspiciatis. Voluptas, quae totam. Numquam sequi eveniet nam!', 
                        'Content for Lesson 2: Saepe animi, tempora quibusdam pariatur, neque atque natus veritatis dolore culpa tempore voluptate aspernatur perspiciatis. Voluptas, quae totam. Numquam sequi eveniet nam! Lorem ipsum dolor sit amet consectetur adipisicing elit.', 
                        'Content for Lesson 3: Lorem ipsum dolor sit amet natus veritatis dolore culpa tempore voluptate aspernatur perspiciatis. Voluptas consectetur adipisicing elit. Saepe animi, tempora quibusdam pariatur, neque atque, quae totam. Numquam sequi eveniet nam!' ];
  lessonNum = 0;
  message = this.lessons[this.lessonNum];

  constructor() { }

  ngOnInit(): void {
    this.lessonNum = 0;
  }

  goPrevious()
  {
    if(this.lessonNum > 0)
    {
      this.lessonNum--;
      this.message = this.lessons[this.lessonNum];
    }
  }

  goNext()
  {
    if(this.lessonNum < this.lessons.length - 1)
    {
      this.lessonNum++;
      this.message = this.lessons[this.lessonNum];
    }
  }

}


