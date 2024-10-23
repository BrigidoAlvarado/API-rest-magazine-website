import { Component } from '@angular/core';
import { SubscriberHeaderComponent } from "../subscriber-header/subscriber-header.component";
import { EditProfileComponent } from "../../user/edit-profile/edit-profile.component";

@Component({
  selector: 'app-subscriber-edit-profile',
  standalone: true,
  imports: [SubscriberHeaderComponent, EditProfileComponent],
  templateUrl: './subscriber-edit-profile.component.html',
  styleUrl: './subscriber-edit-profile.component.css'
})
export class SubscriberEditProfileComponent {

}
