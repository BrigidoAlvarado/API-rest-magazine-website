import { Component } from '@angular/core';
import { AdminHeaderComponent } from "../admin-header/admin-header.component";
import { EditProfileComponent } from "../../user/edit-profile/edit-profile.component";

@Component({
  selector: 'app-admin-edit-profile',
  standalone: true,
  imports: [AdminHeaderComponent, EditProfileComponent],
  templateUrl: './admin-edit-profile.component.html',
  styleUrl: './admin-edit-profile.component.css'
})
export class AdminEditProfileComponent {

}
