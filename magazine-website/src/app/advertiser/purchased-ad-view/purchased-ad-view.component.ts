import { Component, Input } from '@angular/core';
import { Ad } from '../../../entities/ad/ad';
import { AdStatusComponent } from "../../ad/ad-status/ad-status.component";
import { EditAdViewComponent } from "../../ad/edit-ad-view/edit-ad-view.component";
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-purchased-ad-view',
  standalone: true,
  imports: [AdStatusComponent, EditAdViewComponent, RouterModule],
  templateUrl: './purchased-ad-view.component.html',
  styleUrl: './purchased-ad-view.component.css'
})
export class PurchasedAdViewComponent {
  @Input({required: true})
  ad!: Ad;
}
