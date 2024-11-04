import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminMagazineViewComponent } from './admin-magazine-view.component';

describe('AdminMagazineViewComponent', () => {
  let component: AdminMagazineViewComponent;
  let fixture: ComponentFixture<AdminMagazineViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminMagazineViewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminMagazineViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
