import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAdReportsComponent } from './admin-ad-reports.component';

describe('AdminAdReportsComponent', () => {
  let component: AdminAdReportsComponent;
  let fixture: ComponentFixture<AdminAdReportsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminAdReportsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminAdReportsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
