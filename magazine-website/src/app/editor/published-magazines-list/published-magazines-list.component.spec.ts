import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PublishedMagazinesListComponent } from './published-magazines-list.component';

describe('PublishedMagazinesListComponent', () => {
  let component: PublishedMagazinesListComponent;
  let fixture: ComponentFixture<PublishedMagazinesListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PublishedMagazinesListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PublishedMagazinesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
