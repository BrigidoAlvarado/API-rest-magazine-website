import { Component, OnInit } from '@angular/core';
import { Magazine } from '../../../entities/magazine';
import { EditorService } from '../../../services/editor-service';
import { PublishedMagazineViewComponent } from "../published-magazine-view/published-magazine-view.component";
import { CommentLikeStatusComponent } from "../comment-like-status/comment-like-status.component";

@Component({
  selector: 'app-published-magazines-list',
  standalone: true,
  imports: [PublishedMagazineViewComponent, CommentLikeStatusComponent],
  templateUrl: './published-magazines-list.component.html',
  styleUrl: './published-magazines-list.component.css'
})
export class PublishedMagazinesListComponent implements OnInit{

  magazineList!: Magazine[];

  constructor(private service: EditorService){}

  ngOnInit(): void {
      this.service.getPublihsedMagazines().subscribe({
        next: (magazineList: Magazine[]) => {
          console.log('revistas publicadas exito')
          this.magazineList = magazineList;
        }
      });
  }
}
