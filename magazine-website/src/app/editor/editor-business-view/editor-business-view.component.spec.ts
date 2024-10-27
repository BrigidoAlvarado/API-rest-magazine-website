import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditorBusinessViewComponent } from './editor-business-view.component';

describe('EditorBusinessViewComponent', () => {
  let component: EditorBusinessViewComponent;
  let fixture: ComponentFixture<EditorBusinessViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditorBusinessViewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditorBusinessViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
