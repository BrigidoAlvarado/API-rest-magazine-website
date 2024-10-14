import { Component } from '@angular/core';
import { ProfileViewComponent } from '../profile-view/profile-view.component';
import { Profile } from '../../../entities/profile';
@Component({
  selector: 'app-show-profile',
  standalone: true,
  imports: [ProfileViewComponent],
  templateUrl: './show-profile.component.html',
  styleUrl: './show-profile.component.css'
})
export class ShowProfileComponent {
  profile?: Profile;

}
