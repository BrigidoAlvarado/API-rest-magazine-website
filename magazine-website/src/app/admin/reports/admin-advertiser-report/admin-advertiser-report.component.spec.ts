import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAdvertiserReportComponent } from './admin-advertiser-report.component';

describe('AdminAdvertiserReportComponent', () => {
  let component: AdminAdvertiserReportComponent;
  let fixture: ComponentFixture<AdminAdvertiserReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminAdvertiserReportComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminAdvertiserReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
