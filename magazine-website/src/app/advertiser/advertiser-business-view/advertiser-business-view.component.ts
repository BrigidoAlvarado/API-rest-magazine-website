import { Component } from '@angular/core';
import { PaymentToUpComponent } from "../../user/payment-to-up/payment-to-up.component";
import { AdvHeaderComponent } from "../adv-header/adv-header.component";
import { TextAdComponent } from "../../ad/text-ad/text-ad.component";

@Component({
  selector: 'app-advertiser-business-view',
  standalone: true,
  imports: [PaymentToUpComponent, AdvHeaderComponent, TextAdComponent],
  templateUrl: './advertiser-business-view.component.html',
  styleUrl: './advertiser-business-view.component.css'
})
export class AdvertiserBusinessViewComponent {

}
