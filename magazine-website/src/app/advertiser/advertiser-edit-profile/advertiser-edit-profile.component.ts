import { Component } from '@angular/core';
import { AdvHeaderComponent } from "../adv-header/adv-header.component";
import { EditProfileComponent } from "../../user/edit-profile/edit-profile.component";

@Component({
  selector: 'app-advertiser-edit-profile',
  standalone: true,
  imports: [AdvHeaderComponent, EditProfileComponent],
  templateUrl: './advertiser-edit-profile.component.html',
  styleUrl: './advertiser-edit-profile.component.css'
})
export class AdvertiserEditProfileComponent {

}
