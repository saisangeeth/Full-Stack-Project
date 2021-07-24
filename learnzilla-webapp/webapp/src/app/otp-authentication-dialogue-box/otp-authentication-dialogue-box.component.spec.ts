import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OtpAuthenticationDialogueBoxComponent } from './otp-authentication-dialogue-box.component';

describe('OtpAuthenticationDialogueBoxComponent', () => {
  let component: OtpAuthenticationDialogueBoxComponent;
  let fixture: ComponentFixture<OtpAuthenticationDialogueBoxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OtpAuthenticationDialogueBoxComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OtpAuthenticationDialogueBoxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
