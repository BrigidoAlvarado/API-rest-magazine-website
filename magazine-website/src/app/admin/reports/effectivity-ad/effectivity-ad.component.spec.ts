import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EffectivityAdComponent } from './effectivity-ad.component';

describe('EffectivityAdComponent', () => {
  let component: EffectivityAdComponent;
  let fixture: ComponentFixture<EffectivityAdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EffectivityAdComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EffectivityAdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
