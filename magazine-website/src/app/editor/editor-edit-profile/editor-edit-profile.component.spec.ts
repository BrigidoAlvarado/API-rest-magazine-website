import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditorEditProfileComponent } from './editor-edit-profile.component';

describe('EditorEditProfileComponent', () => {
  let component: EditorEditProfileComponent;
  let fixture: ComponentFixture<EditorEditProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditorEditProfileComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditorEditProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
