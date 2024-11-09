import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditorCommentReportComponent } from './editor-comment-report.component';

describe('EditorCommentReportComponent', () => {
  let component: EditorCommentReportComponent;
  let fixture: ComponentFixture<EditorCommentReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditorCommentReportComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditorCommentReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
