import { Component, Input } from '@angular/core';
import { Magazine } from '../../../entities/magazine';
import { SubscribeComponent } from "../subscribe/subscribe.component";
import { ExplorerButtomsComponent } from "../explorer-buttoms/explorer-buttoms.component";

@Component({
  selector: 'app-magazine-explorer-view',
  standalone: true,
  imports: [SubscribeComponent, ExplorerButtomsComponent],
  templateUrl: './magazine-explorer-view.component.html',
  styleUrl: './magazine-explorer-view.component.css'
})
export class MagazineExplorerViewComponent {

  @Input({required: true})
  magazine!: Magazine;
}
