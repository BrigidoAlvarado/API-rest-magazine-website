import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ProfileService } from '.././../../services/profile/profile-service';
import { UsersHome } from '../../../services/users-home';
import { AuthService} from '../../../services/auth';
import { Profile} from '../../../entities/profile';

@Component({
  selector: 'app-edit-profile-view',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './edit-profile-view.component.html',
  styleUrl: './edit-profile-view.component.css'
})
export class EditProfileViewComponent implements OnInit {
@Input({required: true})
  profile!: Profile;
  uploadFileForm!: FormGroup;
  selectedFile: File | null = null;

  constructor(private formBuilder: FormBuilder, private profileService: ProfileService, private router: UsersHome, private auth: AuthService){
  }

  ngOnInit(): void {
    console.log('en on init');
    this.uploadFileForm = this.formBuilder.group({
      userName: [this.profile.userName],
      tastes: [this.profile.tastes],
      topicOfInterest: [this.profile.topicOfInterest],
      description: [this.profile.description],
      hobbies: [this.profile.hobbies],
      photo: [null]
    });
  }

  onFileChange(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      const file = input.files[0];
      this.selectedFile = file;
    } else {
      this.selectedFile = null;
    }
  }

  submit(): void {
    if (this.uploadFileForm.valid) {
      this.profile = this.uploadFileForm.value as Profile;
      if (this.selectedFile instanceof File) {
        this.profile.photo = this.selectedFile;
      }
      this.profileService.editProfile(this.profile).subscribe({
        next: () => {
          this.uploadFileForm.reset();
          this.selectedFile = null;
          this.router.redirectToProfile(this.auth.getUserType());
        },
        error: (error: any) => {
          console.log('se hallo un error');
          console.log(error);
        }
      });
    }
  }
}
