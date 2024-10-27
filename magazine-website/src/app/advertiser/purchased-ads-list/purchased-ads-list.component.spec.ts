import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PurchasedAdsListComponent } from './purchased-ads-list.component';

describe('PurchasedAdsListComponent', () => {
  let component: PurchasedAdsListComponent;
  let fixture: ComponentFixture<PurchasedAdsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PurchasedAdsListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PurchasedAdsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
