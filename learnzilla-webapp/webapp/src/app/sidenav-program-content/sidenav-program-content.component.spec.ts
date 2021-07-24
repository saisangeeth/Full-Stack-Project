import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SidenavProgramContentComponent } from './sidenav-program-content.component';

describe('SidenavProgramContentComponent', () => {
  let component: SidenavProgramContentComponent;
  let fixture: ComponentFixture<SidenavProgramContentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SidenavProgramContentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SidenavProgramContentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
