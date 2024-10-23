import { Component } from '@angular/core';
import { AdvHeaderComponent } from "../adv-header/adv-header.component";
import { ProfileRequestComponent } from "../../user/profile-request/ad-profile.component";

@Component({
  selector: 'app-adv-profile',
  standalone: true,
  imports: [AdvHeaderComponent, ProfileRequestComponent],
  templateUrl: './adv-profile.component.html',
  styleUrl: './adv-profile.component.css'
})
export class AdvProfileComponent {

}
