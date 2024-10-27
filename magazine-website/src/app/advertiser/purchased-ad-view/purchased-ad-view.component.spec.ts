import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PurchasedAdViewComponent } from './purchased-ad-view.component';

describe('PurchasedAdViewComponent', () => {
  let component: PurchasedAdViewComponent;
  let fixture: ComponentFixture<PurchasedAdViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PurchasedAdViewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PurchasedAdViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
