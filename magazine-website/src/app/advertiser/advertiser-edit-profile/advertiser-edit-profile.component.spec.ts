import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvertiserEditProfileComponent } from './advertiser-edit-profile.component';

describe('AdvertiserEditProfileComponent', () => {
  let component: AdvertiserEditProfileComponent;
  let fixture: ComponentFixture<AdvertiserEditProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdvertiserEditProfileComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdvertiserEditProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
