import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditTextAdFormComponent } from './edit-text-ad-form.component';

describe('EditTextAdFormComponent', () => {
  let component: EditTextAdFormComponent;
  let fixture: ComponentFixture<EditTextAdFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditTextAdFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditTextAdFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
