import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateAdStatusComponent } from './update-ad-status.component';

describe('UpdateAdStatusComponent', () => {
  let component: UpdateAdStatusComponent;
  let fixture: ComponentFixture<UpdateAdStatusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpdateAdStatusComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateAdStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
