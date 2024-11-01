import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PublishedMagazineViewComponent } from './published-magazine-view.component';

describe('PublishedMagazineViewComponent', () => {
  let component: PublishedMagazineViewComponent;
  let fixture: ComponentFixture<PublishedMagazineViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PublishedMagazineViewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PublishedMagazineViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
