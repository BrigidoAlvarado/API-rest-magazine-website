import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvertiserHomeComponent } from './advertiser-home.component';

describe('AdvertiserHomeComponent', () => {
  let component: AdvertiserHomeComponent;
  let fixture: ComponentFixture<AdvertiserHomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdvertiserHomeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdvertiserHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
