import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProfileService } from '../../../services/profile/profile-service';
import { AuthService } from '../../../services/auth';
import { Profile } from '../../../entities/profile';
import { ProfileViewComponent } from '../../user/profile-view/profile-view.component';
import { SubscriberHeaderComponent } from "../subscriber-header/subscriber-header.component";

@Component({
  selector: 'app-autor-profile',
  standalone: true,
  imports: [ProfileViewComponent, SubscriberHeaderComponent],
  templateUrl: './autor-profile.component.html',
  styleUrl: './autor-profile.component.css'
})
export class AutorProfileComponent implements OnInit {

  userName!: string;
  usertType: string = 'editor';
  profile!: Profile;

  constructor(
    private route: ActivatedRoute, 
    private service: ProfileService, 
    private auth: AuthService,
    private router: Router){}

  ngOnInit(): void {

    this.userName = this.route.snapshot.params['userName'];
    console.log('userName is: ',this.userName);
    
    this.service.getProfile(this.userName,this.usertType).subscribe({
      next: (profile: Profile) => {
        this.profile = profile;
      },
      error: (error: any) => {
        console.log(error);
        this.auth.validate(error);
        this.router.navigate(['/subscriber-explorer']);
      }
    })
  }

}
