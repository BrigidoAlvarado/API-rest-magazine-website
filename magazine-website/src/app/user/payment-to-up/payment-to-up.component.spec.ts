import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PaymentToUpComponent } from './payment-to-up.component';

describe('PaymentToUpComponent', () => {
  let component: PaymentToUpComponent;
  let fixture: ComponentFixture<PaymentToUpComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PaymentToUpComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PaymentToUpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
