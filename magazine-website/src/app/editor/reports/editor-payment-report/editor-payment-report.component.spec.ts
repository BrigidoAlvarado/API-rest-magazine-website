import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditorPaymentReportComponent } from './editor-payment-report.component';

describe('EditorPaymentReportComponent', () => {
  let component: EditorPaymentReportComponent;
  let fixture: ComponentFixture<EditorPaymentReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditorPaymentReportComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditorPaymentReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
