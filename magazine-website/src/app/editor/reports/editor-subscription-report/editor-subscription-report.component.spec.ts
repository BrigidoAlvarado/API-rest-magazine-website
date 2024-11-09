import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditorSubscriptionReportComponent } from './editor-subscription-report.component';

describe('EditorSubscriptionReportComponent', () => {
  let component: EditorSubscriptionReportComponent;
  let fixture: ComponentFixture<EditorSubscriptionReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditorSubscriptionReportComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditorSubscriptionReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
