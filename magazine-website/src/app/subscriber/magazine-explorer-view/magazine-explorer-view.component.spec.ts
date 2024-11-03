import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MagazineExplorerViewComponent } from './magazine-explorer-view.component';

describe('MagazineExplorerViewComponent', () => {
  let component: MagazineExplorerViewComponent;
  let fixture: ComponentFixture<MagazineExplorerViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MagazineExplorerViewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MagazineExplorerViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
