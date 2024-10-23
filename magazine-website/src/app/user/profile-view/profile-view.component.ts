import { Component, Input, OnInit } from '@angular/core';
import { Profile } from '../../../entities/profile'
import { AuthService } from '../../../services/auth';

@Component({
  selector: 'app-profile-view',
  standalone: true,
  imports: [],
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