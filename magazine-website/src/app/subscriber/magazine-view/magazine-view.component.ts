import { Component, OnInit } from '@angular/core';
import { SubscriberHeaderComponent } from "../subscriber-header/subscriber-header.component";
import { AuthService } from '../../../services/auth';
import { SubscriberService } from '../../../services/subscriber-service';
import { Magazine } from '../../../entities/magazine';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { CommentComponent } from "../comment/comment.component";
import { LikeComponent } from "../like/like.component";
import { FileService } from '../../../services/file-service';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { ShowAdComponent } from "../../ad/show-ad/show-ad.component";

@Component({
  selector: 'app-magazine-view',
  standalone: true,
  imports: [SubscriberHeaderComponent, RouterModule, CommentComponent, LikeComponent, ShowAdComponent],
  templateUrl: './magazine-view.component.html',
  styleUrl: './magazine-view.component.css'
})
export class MagazineViewComponent implements OnInit {
  url: string = 'magazine/:id';
  id!: number;
  magazine!: Magazine;
  fileSelected!: number;
  pdfUrl: SafeResourceUrl| null = null;

  constructor(
    private service:SubscriberService, 
    private auth: AuthService, 
    private route: ActivatedRoute,
    private fileService: FileService,
    private sanitizer: DomSanitizer
  ){}

  ngOnInit(): void {
      this.id = this.route.snapshot.params['id'];
      console.log('id is: ',this.id);
      this.callServie();

  }

  chooseFile(id: number):void {
    this.fileSelected = id;
    this.fileService.getPdf(id).subscribe({
      next: (blob: Blob) => {
        const objectUrl = URL.createObjectURL(blob);
        this.pdfUrl = this.sanitizer.bypassSecurityTrustResourceUrl(objectUrl);
      },
      error: (error: any) => {
        console.log('error al cargar el pdf',error);
        this.auth.validate(error);
      }
    })
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
