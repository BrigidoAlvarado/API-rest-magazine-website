import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GlobalCostListComponent } from './global-cost-list.component';

describe('GlobalCostListComponent', () => {
  let component: GlobalCostListComponent;
  let fixture: ComponentFixture<GlobalCostListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GlobalCostListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GlobalCostListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
