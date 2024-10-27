import { Component, Input, OnInit } from '@angular/core';
import { Profile } from '../../../entities/profile'
import { AuthService } from '../../../services/auth';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-profile-view',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './profile-view.component.html',
  styleUrl: './profile-view.component.css'
})
export class ProfileViewComponent{
  @Input({required: true})
  profile!: Profile;
  userName: string | null;
  edit: boolean = false;

  constructor(private auth: AuthService){
    this.userName = auth.getUserName();
  }
}