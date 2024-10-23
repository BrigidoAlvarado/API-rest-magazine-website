import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubscriberEditProfileComponent } from './subscriber-edit-profile.component';

describe('SubscriberEditProfileComponent', () => {
  let component: SubscriberEditProfileComponent;
  let fixture: ComponentFixture<SubscriberEditProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SubscriberEditProfileComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SubscriberEditProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
