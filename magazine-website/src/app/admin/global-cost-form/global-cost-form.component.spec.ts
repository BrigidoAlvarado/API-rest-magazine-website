import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GlobalCostFormComponent } from './global-cost-form.component';

describe('GlobalCostFormComponent', () => {
  let component: GlobalCostFormComponent;
  let fixture: ComponentFixture<GlobalCostFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GlobalCostFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GlobalCostFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
