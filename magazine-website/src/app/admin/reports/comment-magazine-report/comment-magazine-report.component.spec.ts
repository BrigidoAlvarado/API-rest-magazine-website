import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommentMagazineReportComponent } from './comment-magazine-report.component';

describe('CommentMagazineReportComponent', () => {
  let component: CommentMagazineReportComponent;
  let fixture: ComponentFixture<CommentMagazineReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CommentMagazineReportComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CommentMagazineReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
