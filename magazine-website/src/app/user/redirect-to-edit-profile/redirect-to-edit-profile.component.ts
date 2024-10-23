import { Component, OnInit } from '@angular/core';
import { UsersHome} from '../../../services/users-home';

@Component({
  selector: 'app-redirect-to-edit-profile',
  standalone: true,
  imports: [],
  templateUrl: './redirect-to-edit-profile.component.html',
  styleUrl: './redirect-to-edit-profile.component.css'
})
export class RedirectToEditProfileComponent implements OnInit{

  constructor(private userHome: UsersHome){}

  ngOnInit(): void {
      this.userHome.redirectToEditProfile();
  }

}
