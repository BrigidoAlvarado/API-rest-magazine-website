import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditVideoAdFormComponent } from './edit-video-ad-form.component';

describe('EditVideoAdFormComponent', () => {
  let component: EditVideoAdFormComponent;
  let fixture: ComponentFixture<EditVideoAdFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditVideoAdFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditVideoAdFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
