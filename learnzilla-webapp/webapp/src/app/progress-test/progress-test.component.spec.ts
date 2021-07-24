import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProgressTestComponent } from './progress-test.component';

describe('ProgressTestComponent', () => {
  let component: ProgressTestComponent;
  let fixture: ComponentFixture<ProgressTestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProgressTestComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProgressTestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
