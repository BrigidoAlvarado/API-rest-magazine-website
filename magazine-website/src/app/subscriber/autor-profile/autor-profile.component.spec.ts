import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AutorProfileComponent } from './autor-profile.component';

describe('AutorProfileComponent', () => {
  let component: AutorProfileComponent;
  let fixture: ComponentFixture<AutorProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AutorProfileComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AutorProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
