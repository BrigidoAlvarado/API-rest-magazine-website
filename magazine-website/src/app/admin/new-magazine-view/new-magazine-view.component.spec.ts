import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewMagazineViewComponent } from './new-magazine-view.component';

describe('NewMagazineViewComponent', () => {
  let component: NewMagazineViewComponent;
  let fixture: ComponentFixture<NewMagazineViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NewMagazineViewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewMagazineViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
