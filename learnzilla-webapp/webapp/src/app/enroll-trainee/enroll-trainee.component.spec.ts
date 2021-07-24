import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnrollTraineeComponent } from './enroll-trainee.component';

describe('EnrollTraineeComponent', () => {
  let component: EnrollTraineeComponent;
  let fixture: ComponentFixture<EnrollTraineeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EnrollTraineeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EnrollTraineeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
