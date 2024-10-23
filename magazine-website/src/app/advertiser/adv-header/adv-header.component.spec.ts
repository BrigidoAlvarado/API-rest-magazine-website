import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvHeaderComponent } from './adv-header.component';

describe('AdvHeaderComponent', () => {
  let component: AdvHeaderComponent;
  let fixture: ComponentFixture<AdvHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdvHeaderComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdvHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
