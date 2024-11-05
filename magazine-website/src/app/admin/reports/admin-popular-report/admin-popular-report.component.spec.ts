import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminPopularReportComponent } from './admin-popular-report.component';

describe('AdminPopularReportComponent', () => {
  let component: AdminPopularReportComponent;
  let fixture: ComponentFixture<AdminPopularReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminPopularReportComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminPopularReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
