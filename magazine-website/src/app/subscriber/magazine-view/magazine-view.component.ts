import { Component, Input, OnInit } from '@angular/core';
import { SubscriberHeaderComponent } from "../subscriber-header/subscriber-header.component";
import { AuthService } from '../../../services/auth';
import { SubscriberService } from '../../../services/subscriber-service';
import { Magazine } from '../../../entities/magazine';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { CommentComponent } from "../comment/comment.component";
import { LikeComponent } from "../like/like.component";

@Component({
  selector: 'app-magazine-view',
  standalone: true,
  imports: [SubscriberHeaderComponent, RouterModule, CommentComponent, LikeComponent],
  templateUrl: './magazine-view.component.html',
  styleUrl: './magazine-view.component.css'
})
export class MagazineViewComponent implements OnInit {
  id!: number;
  magazine!: Magazine;

  constructor(
    private service:SubscriberService, 
    private auth: AuthService, 
    private route: ActivatedRoute){}

  ngOnInit(): void {
      this.id = this.route.snapshot.params['id'];
      console.log('id is: ',this.id);
      this.callServie();

  }

 private callServie(): void {
    this.service.getSubscribedMagazineById(this.id).subscribe({
      next: (magazine: Magazine) => {
        this.magazine = magazine;
      },
      error: ( error: any) => {
        console.log('error al traer la revista suscrita ',error);
        this.auth.validate(error);
      }
    });
  }
}
