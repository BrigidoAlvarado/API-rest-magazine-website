import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TextAdComponent } from './text-ad.component';

describe('TextAdComponent', () => {
  let component: TextAdComponent;
  let fixture: ComponentFixture<TextAdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TextAdComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TextAdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
