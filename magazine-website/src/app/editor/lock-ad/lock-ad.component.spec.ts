import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LockAdComponent } from './lock-ad.component';

describe('LockAdComponent', () => {
  let component: LockAdComponent;
  let fixture: ComponentFixture<LockAdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LockAdComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LockAdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
