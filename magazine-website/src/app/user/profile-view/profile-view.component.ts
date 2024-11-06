import { Component, Input, OnInit } from '@angular/core';
import { Profile } from '../../../entities/profile'
import { AuthService } from '../../../services/auth';
import { RouterModule } from '@angular/router';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { FileService } from '../../../services/file-service';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-profile-view',
  standalone: true,
  imports: [RouterModule, NgIf],
  templateUrl: './profile-view.component.html',
  styleUrl: './profile-view.component.css'
})
export class ProfileViewComponent implements OnInit{
  
  @Input({required: true})
  profile!: Profile;
  userName: string | null;
  edit: boolean = false;

  @Input({required: true})
  profileName!: string;
  @Input({required: true})
  profileType!: string;

  profileImageUrl: SafeUrl | null = null;

  constructor(
    private auth: AuthService,
    private fileService: FileService,
    private sanitizer: DomSanitizer){
    this.userName = auth.getUserName();
  }

  ngOnInit(): void {
    console.log('en onInit');

      this.fileService.getPhoto(this.profile.userName, this.profile.userType).subscribe({

        next: (blob: Blob) => {
          const objectUrl = URL.createObjectURL(blob);
          this.profileImageUrl = this.sanitizer.bypassSecurityTrustUrl(objectUrl);
        },
        error: (error: any) => {
          this.auth.validate(error);
          console.log('Error al cargar la imagen de perfil: ',error);
        }
      });
  }
  
}