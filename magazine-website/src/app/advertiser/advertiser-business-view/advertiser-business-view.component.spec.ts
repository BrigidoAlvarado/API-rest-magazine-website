import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvertiserBusinessViewComponent } from './advertiser-business-view.component';

describe('AdvertiserBusinessViewComponent', () => {
  let component: AdvertiserBusinessViewComponent;
  let fixture: ComponentFixture<AdvertiserBusinessViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdvertiserBusinessViewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdvertiserBusinessViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
