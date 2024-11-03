import { Component, Input } from '@angular/core';
import { MagazineExplorerViewComponent } from "../magazine-explorer-view/magazine-explorer-view.component";
import { Magazine } from '../../../entities/magazine';
import { PreviewMagazineComponent } from "../preview-magazine/preview-magazine.component";
import { SubscribeComponent } from "../subscribe/subscribe.component";
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-explorer-buttoms',
  standalone: true,
  imports: [MagazineExplorerViewComponent, PreviewMagazineComponent, SubscribeComponent, RouterModule],
  templateUrl: './explorer-buttoms.component.html',
  styleUrl: './explorer-buttoms.component.css'
})
export class ExplorerButtomsComponent {

  @Input({required: true})
  magazine!: Magazine;
}
