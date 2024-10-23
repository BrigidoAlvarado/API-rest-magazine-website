import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileRequestComponent } from './app-profile-request';

describe('AdProfileComponent', () => {
  let component: ProfileRequestComponent;
  let fixture: ComponentFixture<ProfileRequestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProfileRequestComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfileRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
