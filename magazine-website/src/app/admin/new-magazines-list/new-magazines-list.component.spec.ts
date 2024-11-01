import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewMagazinesListComponent } from './new-magazines-list.component';

describe('NewMagazinesListComponent', () => {
  let component: NewMagazinesListComponent;
  let fixture: ComponentFixture<NewMagazinesListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NewMagazinesListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewMagazinesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
