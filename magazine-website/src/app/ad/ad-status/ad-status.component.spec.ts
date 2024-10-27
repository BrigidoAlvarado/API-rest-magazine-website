import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdStatusComponent } from './ad-status.component';

describe('AdStatusComponent', () => {
  let component: AdStatusComponent;
  let fixture: ComponentFixture<AdStatusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdStatusComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
