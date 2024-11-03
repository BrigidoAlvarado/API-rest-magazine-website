import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubsMagazineListComponent } from './subs-magazine-list.component';

describe('SubsMagazineListComponent', () => {
  let component: SubsMagazineListComponent;
  let fixture: ComponentFixture<SubsMagazineListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SubsMagazineListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SubsMagazineListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
