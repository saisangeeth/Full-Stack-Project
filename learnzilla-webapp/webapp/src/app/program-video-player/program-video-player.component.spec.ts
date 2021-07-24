import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProgramVideoPlayerComponent } from './program-video-player.component';

describe('ProgramVideoPlayerComponent', () => {
  let component: ProgramVideoPlayerComponent;
  let fixture: ComponentFixture<ProgramVideoPlayerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProgramVideoPlayerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProgramVideoPlayerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
