import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdProfileComponent } from './ad-profile.component';

describe('AdProfileComponent', () => {
  let component: AdProfileComponent;
  let fixture: ComponentFixture<AdProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdProfileComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
