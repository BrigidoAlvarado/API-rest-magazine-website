import { Component } from '@angular/core';
import { AdminHeaderComponent } from "../admin-header/admin-header.component";
import { ProfileRequestComponent } from '../../user/profile-request/ad-profile.component';
import { ShowAdComponent } from "../../ad/show-ad/show-ad.component";

@Component({
  selector: 'app-admin-profile',
  standalone: true,
  imports: [AdminHeaderComponent, ProfileRequestComponent, ShowAdComponent],
  templateUrl: './admin-profile.component.html',
  styleUrl: './admin-profile.component.css'
})
export class AdminProfileComponent {
url: string = 'admin-profile';
}
