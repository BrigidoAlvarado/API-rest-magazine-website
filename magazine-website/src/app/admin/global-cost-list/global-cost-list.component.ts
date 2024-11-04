import { Component } from '@angular/core';
import { GlobalCostFormComponent } from '../global-cost-form/global-cost-form.component';

@Component({
  selector: 'app-global-cost-list',
  standalone: true,
  imports: [GlobalCostFormComponent],
  templateUrl: './global-cost-list.component.html',
  styleUrl: './global-cost-list.component.css'
})
export class GlobalCostListComponent {

  globalCostList: string [] = ['lockAd', 'magazine', 'textAd', 'textImageAd', 'videoAd'];
}