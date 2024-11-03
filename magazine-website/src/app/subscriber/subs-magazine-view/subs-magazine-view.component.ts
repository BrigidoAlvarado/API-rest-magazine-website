import { Component, Input } from '@angular/core';
import { Magazine } from '../../../entities/magazine';

@Component({
  selector: 'app-subs-magazine-view',
  standalone: true,
  imports: [],
  templateUrl: './subs-magazine-view.component.html',
  styleUrl: './subs-magazine-view.component.css'
})
export class SubsMagazineViewComponent {

  @Input({required: true})
  magazine!: Magazine;

}
