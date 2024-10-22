import { Component, OnInit } from '@angular/core';
import { AdminHeaderComponent } from "../admin-header/admin-header.component";
import { Profile } from "../../../entities/profile";
import { ProfileViewComponent } from "../../../app/user/profile-view/profile-view.component";
import { ProfileService } from '../../../services/profile/profile-service';

@Component({
  selector: 'app-ad-profile',
  standalone: true,
  imports: [AdminHeaderComponent, ProfileViewComponent],
  templateUrl: './ad-profile.component.html',
  styleUrl: './ad-profile.component.css'
})
export class AdProfileComponent implements OnInit {
  profile!: Profile;
  public readonly PATH = 'admin-profile';

  constructor(private profileService: ProfileService){}

  ngOnInit(): void {
    console.log('se ha iniciado la peticion');
      this.profileService.getProfile().subscribe({
        next: () => {
          console.log('la peticion fue un exito');
        },
        error: (error: any) => {
          console.log(error);
        }
      });
  }

}