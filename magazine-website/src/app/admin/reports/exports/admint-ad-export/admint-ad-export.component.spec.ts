import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdmintAdExportComponent } from './admint-ad-export.component';

describe('AdmintAdExportComponent', () => {
  let component: AdmintAdExportComponent;
  let fixture: ComponentFixture<AdmintAdExportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdmintAdExportComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdmintAdExportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
