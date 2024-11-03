import { Component, OnInit } from '@angular/core';
import { AdminHeaderComponent } from "../../admin/admin-header/admin-header.component";
import { Profile } from "../../../entities/profile";
import { ProfileViewComponent } from "../profile-view/profile-view.component";
import { ProfileService } from '../../../services/profile/profile-service';
import { AuthService } from '../../../services/auth';

@Component({
  selector: 'app-profile-request',
  standalone: true,
  imports: [AdminHeaderComponent, ProfileViewComponent],
  templateUrl: './app-profile-request.html',
  styleUrl: './ad-profile.component.css'
})
export class ProfileRequestComponent implements OnInit {
  profile!: Profile;
  //public readonly PATH = 'admin-profile';

  constructor(private profileService: ProfileService, private auth: AuthService){}

  ngOnInit(): void {
    let userName = this.auth.getUserName();
    let userType = this.auth.getUserType();
      this.profileService.getProfile(userName, userType).subscribe({
        next: (profile: Profile) => {
          console.log('la peticion fue un exito');
          this.profile = profile;
        },
        error: (error: any) => {
          this.auth.validate(error);
          console.log(error);
        }
      });
  }

}