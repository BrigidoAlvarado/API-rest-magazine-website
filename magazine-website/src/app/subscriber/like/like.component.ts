import { Component, input, Input, OnInit } from '@angular/core';
import { SubscriberService } from '../../../services/subscriber-service';
import { AuthService } from '../../../services/auth';

@Component({
  selector: 'app-like',
  standalone: true,
  imports: [],
  templateUrl: './like.component.html',
  styleUrl: './like.component.css'
})
export class LikeComponent{

  @Input({required: true})
  isLiked!: boolean;
  @Input({required: true})
  commentStatus!: boolean;
  @Input({required: true})
  id!: number;

  constructor(private service: SubscriberService, private auth: AuthService){ }

  submit(): void {
    console.log('se hizo submit');
    this.service.like(this.id).subscribe({
      next: () => {
        window.location.reload();
      },
      error: (error: any) => {
        console.log(error);
        this.auth.validate(error);
      }
    })
  }
}
