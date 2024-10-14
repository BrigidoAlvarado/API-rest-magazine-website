import { Component, Input } from '@angular/core';
import { Profile } from '../../../entities/profile'

@Component({
  selector: 'app-profile-view',
  standalone: true,
  imports: [],
  templateUrl: './profile-view.component.html',
  styleUrl: './profile-view.component.css'
})
export class ProfileViewComponent {
  @Input({required: true})
  profile!: Profile;
}
