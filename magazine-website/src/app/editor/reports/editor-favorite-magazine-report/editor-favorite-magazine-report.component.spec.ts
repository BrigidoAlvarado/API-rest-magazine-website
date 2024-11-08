import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditorFavoriteMagazineReportComponent } from './editor-favorite-magazine-report.component';

describe('EditorFavoriteMagazineReportComponent', () => {
  let component: EditorFavoriteMagazineReportComponent;
  let fixture: ComponentFixture<EditorFavoriteMagazineReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditorFavoriteMagazineReportComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditorFavoriteMagazineReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
