import { Component } from '@angular/core';
import { AdvHeaderComponent } from "../adv-header/adv-header.component";

@Component({
  selector: 'app-advertiser-home',
  standalone: true,
  imports: [AdvHeaderComponent],
  templateUrl: './advertiser-home.component.html',
  styleUrl: './advertiser-home.component.css'
})
export class AdvertiserHomeComponent {

}
