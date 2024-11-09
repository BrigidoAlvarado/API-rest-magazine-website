import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditImageAdFormComponent } from './edit-image-ad-form.component';

describe('EditImageAdFormComponent', () => {
  let component: EditImageAdFormComponent;
  let fixture: ComponentFixture<EditImageAdFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditImageAdFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditImageAdFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
