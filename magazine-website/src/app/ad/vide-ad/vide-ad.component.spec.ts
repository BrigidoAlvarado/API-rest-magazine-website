import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VideAdComponent } from './vide-ad.component';

describe('VideAdComponent', () => {
  let component: VideAdComponent;
  let fixture: ComponentFixture<VideAdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VideAdComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VideAdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
