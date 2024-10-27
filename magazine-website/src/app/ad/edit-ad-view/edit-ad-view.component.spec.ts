import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditAdViewComponent } from './edit-ad-view.component';

describe('EditAdViewComponent', () => {
  let component: EditAdViewComponent;
  let fixture: ComponentFixture<EditAdViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditAdViewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditAdViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
