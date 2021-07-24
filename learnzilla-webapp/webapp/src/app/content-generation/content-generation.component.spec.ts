import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContentGenerationComponent } from './content-generation.component';

describe('ContentGenerationComponent', () => {
  let component: ContentGenerationComponent;
  let fixture: ComponentFixture<ContentGenerationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ContentGenerationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ContentGenerationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
