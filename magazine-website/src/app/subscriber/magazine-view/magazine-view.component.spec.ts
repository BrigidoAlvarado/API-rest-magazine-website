import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MagazineViewComponent } from './magazine-view.component';

describe('MagazineViewComponent', () => {
  let component: MagazineViewComponent;
  let fixture: ComponentFixture<MagazineViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MagazineViewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MagazineViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
