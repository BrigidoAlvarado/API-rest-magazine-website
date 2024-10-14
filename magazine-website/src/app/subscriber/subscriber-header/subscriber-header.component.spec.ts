import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubscriberHeaderComponent } from './subscriber-header.component';

describe('SubscriberHeaderComponent', () => {
  let component: SubscriberHeaderComponent;
  let fixture: ComponentFixture<SubscriberHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SubscriberHeaderComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SubscriberHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
