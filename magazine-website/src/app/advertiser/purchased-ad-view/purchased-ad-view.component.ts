import { Component, Input } from '@angular/core';
import { Ad } from '../../../entities/ad/ad';
import { AdStatusComponent } from "../../ad/ad-status/ad-status.component";

@Component({
  selector: 'app-purchased-ad-view',
  standalone: true,
  imports: [AdStatusComponent],
  templateUrl: './purchased-ad-view.component.html',
  styleUrl: './purchased-ad-view.component.css'
})
export class PurchasedAdViewComponent {
  @Input({required: true})
  ad!: Ad;
}
