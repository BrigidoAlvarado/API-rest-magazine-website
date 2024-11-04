import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ImageAdComponent } from './image-ad.component';

describe('ImageAdComponent', () => {
  let component: ImageAdComponent;
  let fixture: ComponentFixture<ImageAdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ImageAdComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ImageAdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
