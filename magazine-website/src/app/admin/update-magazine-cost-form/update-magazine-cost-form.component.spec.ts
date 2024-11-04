import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateMagazineCostFormComponent } from './update-magazine-cost-form.component';

describe('UpdateMagazineCostFormComponent', () => {
  let component: UpdateMagazineCostFormComponent;
  let fixture: ComponentFixture<UpdateMagazineCostFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpdateMagazineCostFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateMagazineCostFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
