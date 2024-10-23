import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditProfileViewComponent } from './edit-profile-view.component';

describe('EditProfileViewComponent', () => {
  let component: EditProfileViewComponent;
  let fixture: ComponentFixture<EditProfileViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditProfileViewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditProfileViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
