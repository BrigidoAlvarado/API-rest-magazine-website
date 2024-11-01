import { Component, Input } from '@angular/core';
import { Magazine } from '../../../entities/magazine';

@Component({
  selector: 'app-pos-magazine-view',
  standalone: true,
  imports: [],
  templateUrl: './pos-magazine-view.component.html',
  styleUrl: './pos-magazine-view.component.css'
})
export class PosMagazineViewComponent {

  @Input({required: true})
  magazine!: Magazine;
}
