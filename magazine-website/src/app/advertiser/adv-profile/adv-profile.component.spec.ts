import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvProfileComponent } from './adv-profile.component';

describe('AdvProfileComponent', () => {
  let component: AdvProfileComponent;
  let fixture: ComponentFixture<AdvProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdvProfileComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdvProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
