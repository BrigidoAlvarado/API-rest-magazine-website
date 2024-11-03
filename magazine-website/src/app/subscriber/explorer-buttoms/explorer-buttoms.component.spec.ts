import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExplorerButtomsComponent } from './explorer-buttoms.component';

describe('ExplorerButtomsComponent', () => {
  let component: ExplorerButtomsComponent;
  let fixture: ComponentFixture<ExplorerButtomsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ExplorerButtomsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExplorerButtomsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
