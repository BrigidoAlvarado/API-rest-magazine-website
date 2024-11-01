import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PosMagazineViewComponent } from './pos-magazine-view.component';

describe('PosMagazineViewComponent', () => {
  let component: PosMagazineViewComponent;
  let fixture: ComponentFixture<PosMagazineViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PosMagazineViewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PosMagazineViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
