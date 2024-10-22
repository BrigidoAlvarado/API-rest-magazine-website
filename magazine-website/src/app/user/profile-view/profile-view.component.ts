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
export class ProfileViewComponent implements OnInit {
  @Input({required: true})
  profile!: Profile;
  edit: boolean = false;

  constructor(private auth: AuthService){}

  ngOnInit(): void {
    console.log('el perfil del usuario es: ',this.auth.getUserName());  
    let myProfile = this.auth.getUserName(); 
  }

}
