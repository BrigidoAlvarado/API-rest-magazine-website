import { Component, Input } from '@angular/core';
import { Ad } from '../../../entities/ad/ad';
import { PurchasedAdViewComponent } from "../purchased-ad-view/purchased-ad-view.component";

@Component({
  selector: 'app-purchased-ads-list',
  standalone: true,
  imports: [PurchasedAdViewComponent],
  templateUrl: './purchased-ads-list.component.html',
  styleUrl: './purchased-ads-list.component.css'
})

export class PurchasedAdsListComponent {
  @Input({required: true})
  ads!: Ad[];
}
