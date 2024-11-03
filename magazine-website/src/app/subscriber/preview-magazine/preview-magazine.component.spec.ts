import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PreviewMagazineComponent } from './preview-magazine.component';

describe('PreviewMagazineComponent', () => {
  let component: PreviewMagazineComponent;
  let fixture: ComponentFixture<PreviewMagazineComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PreviewMagazineComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PreviewMagazineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
