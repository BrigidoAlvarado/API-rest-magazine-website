import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubsMagazineViewComponent } from './subs-magazine-view.component';

describe('SubsMagazineViewComponent', () => {
  let component: SubsMagazineViewComponent;
  let fixture: ComponentFixture<SubsMagazineViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SubsMagazineViewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SubsMagazineViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
