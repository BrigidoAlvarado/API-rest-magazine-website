import { Component, OnInit } from '@angular/core';
import { Profile } from '../../../entities/profile';
import { ProfileService} from '../../../services/profile/profile-service'
import { EditProfileViewComponent } from "../edit-profile-view/edit-profile-view.component";

@Component({
  selector: 'app-edit-profile',
  standalone: true,
  imports: [EditProfileViewComponent],
  templateUrl: './edit-profile.component.html',
  styleUrl: './edit-profile.component.css'
})
export class EditProfileComponent implements OnInit{
  profile!: Profile;

  constructor(private profileService: ProfileService){ }

  ngOnInit(): void {
    this.profileService.getProfile().subscribe({
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