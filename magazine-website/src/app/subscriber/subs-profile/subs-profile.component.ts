import { Component } from '@angular/core';
import { SubscriberHeaderComponent } from "../subscriber-header/subscriber-header.component";
import { ProfileRequestComponent } from "../../user/profile-request/ad-profile.component";

@Component({
  selector: 'app-subs-profile',
  standalone: true,
  imports: [SubscriberHeaderComponent, ProfileRequestComponent],
  templateUrl: './subs-profile.component.html',
  styleUrl: './subs-profile.component.css'
})
export class SubsProfileComponent {

}
