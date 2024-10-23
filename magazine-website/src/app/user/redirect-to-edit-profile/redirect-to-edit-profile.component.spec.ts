import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RedirectToEditProfileComponent } from './redirect-to-edit-profile.component';

describe('RedirectToEditProfileComponent', () => {
  let component: RedirectToEditProfileComponent;
  let fixture: ComponentFixture<RedirectToEditProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RedirectToEditProfileComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RedirectToEditProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
