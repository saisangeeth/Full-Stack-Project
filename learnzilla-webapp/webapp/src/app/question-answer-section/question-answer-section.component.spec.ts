import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuestionAnswerSectionComponent } from './question-answer-section.component';

describe('QuestionAnswerSectionComponent', () => {
  let component: QuestionAnswerSectionComponent;
  let fixture: ComponentFixture<QuestionAnswerSectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ QuestionAnswerSectionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(QuestionAnswerSectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
