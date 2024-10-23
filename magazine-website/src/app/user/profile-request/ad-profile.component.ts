import { Component, OnInit } from '@angular/core';
import { AdminHeaderComponent } from "../../admin/admin-header/admin-header.component";
import { Profile } from "../../../entities/profile";
import { ProfileViewComponent } from "../profile-view/profile-view.component";
import { ProfileService } from '../../../services/profile/profile-service';

@Component({
  selector: 'app-profile-request',
  standalone: true,
  imports: [AdminHeaderComponent, ProfileViewComponent],
  templateUrl: './app-profile-request.html',
  styleUrl: './ad-profile.component.css'
})
export class ProfileRequestComponent implements OnInit {
  profile!: Profile;
  public readonly PATH = 'admin-profile';

  constructor(private profileService: ProfileService){}

  ngOnInit(): void {
    console.log('se ha iniciado la peticion');
      this.profileService.getProfile().subscribe({
        next: (profile: Profile) => {
          console.log('la peticion fue un exito');
          this.profile = profile;
        },
        error: (error: any) => {
          console.log(error);
        }
      });
  }

}