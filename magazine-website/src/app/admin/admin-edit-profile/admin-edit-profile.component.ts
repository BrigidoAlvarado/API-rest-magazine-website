import { Component } from '@angular/core';
import { AdminHeaderComponent } from "../admin-header/admin-header.component";
import { EditProfileComponent } from "../../user/edit-profile/edit-profile.component";
import { ShowAdComponent } from "../../ad/show-ad/show-ad.component";

@Component({
  selector: 'app-admin-edit-profile',
  standalone: true,
  imports: [AdminHeaderComponent, EditProfileComponent, ShowAdComponent],
  templateUrl: './admin-edit-profile.component.html',
  styleUrl: './admin-edit-profile.component.css'
})
export class AdminEditProfileComponent {
url: string = 'admin-edit-profile';
}
