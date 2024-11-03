import { Component, OnInit } from '@angular/core';
import { Profile } from '../../../entities/profile';
import { ProfileService} from '../../../services/profile/profile-service'
import { EditProfileViewComponent } from "../edit-profile-view/edit-profile-view.component";
import { AuthService } from '../../../services/auth';

@Component({
  selector: 'app-edit-profile',
  standalone: true,
  imports: [EditProfileViewComponent],
  templateUrl: './edit-profile.component.html',
  styleUrl: './edit-profile.component.css'
})
export class EditProfileComponent implements OnInit{
  profile!: Profile;

  constructor(private profileService: ProfileService, private auth: AuthService){ }

  ngOnInit(): void {
    let userName = this.auth.getUserName();
    let userType = this.auth.getUserType();
    this.profileService.getProfile(userName, userType).subscribe({
      next: (profile: Profile) => {
        console.log('la peticion fue un exito');
        this.profile = profile;
        console.log(this.profile.userName,this.profile.description,this.profile.hobbies,this.profile.tastes);
      },
      error: (error: any) => {
        console.log(error);
      }
    });
  }
}