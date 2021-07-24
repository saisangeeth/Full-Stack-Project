import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProgramCertificateComponent } from './program-certificate.component';

describe('ProgramCertificateComponent', () => {
  let component: ProgramCertificateComponent;
  let fixture: ComponentFixture<ProgramCertificateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProgramCertificateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProgramCertificateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
