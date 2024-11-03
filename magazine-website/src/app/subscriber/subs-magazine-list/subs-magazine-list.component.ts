import { Component, OnInit } from '@angular/core';
import { Magazine } from '../../../entities/magazine';
import { SubscriberService } from '../../../services/subscriber-service';
import { AuthService } from '../../../services/auth';
import { SubsMagazineViewComponent } from "../subs-magazine-view/subs-magazine-view.component";
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-subs-magazine-list',
  standalone: true,
  imports: [SubsMagazineViewComponent, RouterModule],
  templateUrl: './subs-magazine-list.component.html',
  styleUrl: './subs-magazine-list.component.css'
})
export class SubsMagazineListComponent implements OnInit {
  magazineList!: Magazine[];

  constructor(private service: SubscriberService, private auth: AuthService){}

  ngOnInit(): void {
      this.service.getSubscribedMagazineList().subscribe({
        next: (magazineList: Magazine[]) => {
          console.log('revistas suscritas success');
          this.magazineList = magazineList;
        },
        error: (error: any) => {
          console.log(error);
          this.auth.validate(error);
        }
      });
  }

}
