import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvertiserHeaderComponent } from './advertiser-header.component';

describe('AdvertiserHeaderComponent', () => {
  let component: AdvertiserHeaderComponent;
  let fixture: ComponentFixture<AdvertiserHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdvertiserHeaderComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdvertiserHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
