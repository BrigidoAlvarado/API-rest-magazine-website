import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowAdBodyComponent } from './show-ad-body.component';

describe('ShowAdBodyComponent', () => {
  let component: ShowAdBodyComponent;
  let fixture: ComponentFixture<ShowAdBodyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShowAdBodyComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowAdBodyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
