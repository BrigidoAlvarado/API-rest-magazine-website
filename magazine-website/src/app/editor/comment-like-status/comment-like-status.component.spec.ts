import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommentLikeStatusComponent } from './comment-like-status.component';

describe('CommentLikeStatusComponent', () => {
  let component: CommentLikeStatusComponent;
  let fixture: ComponentFixture<CommentLikeStatusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CommentLikeStatusComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CommentLikeStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
