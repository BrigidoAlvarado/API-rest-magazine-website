import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostMagazineFormComponent } from './post-magazine-form.component';

describe('PostMagazineFormComponent', () => {
  let component: PostMagazineFormComponent;
  let fixture: ComponentFixture<PostMagazineFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PostMagazineFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostMagazineFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
