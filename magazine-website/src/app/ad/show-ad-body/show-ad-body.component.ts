import { Component, Input, OnInit } from '@angular/core';
import { AdService } from '../../../services/ad-service';
import { FileService } from '../../../services/file-service';
import { AuthService } from '../../../services/auth';
import { DomSanitizer, SafeResourceUrl, SafeUrl } from '@angular/platform-browser';
import { Ad } from '../../../entities/ad/ad';

@Component({
  selector: 'app-show-ad-body',
  standalone: true,
  imports: [],
  templateUrl: './show-ad-body.component.html',
  styleUrl: './show-ad-body.component.css'
})
export class ShowAdBodyComponent implements OnInit {

  @Input({required: true})
  url!: string;

  @Input({required: true})
  type!: string;
  
  @Input({required: true})
  editor!: string;
  
  videoUrl: SafeResourceUrl | null = null;
  imageUrl: SafeUrl | null = null;

  ad!: Ad;

  constructor(
    private service: AdService,
    private fileService: FileService,
    private auth: AuthService,
    private sanitizer: DomSanitizer
  ){ }

  ngOnInit(): void {
    if(this.editor){
      this.callWhitEditor();   
    } else {
      this.simpleCall();
    }
  }

  private callWhitEditor(): void {
    this.service.getRandomAdWhitEditor(this.type, this.url, this.editor).subscribe({
      next: (ad: Ad) => {
        this.ad = ad;
        this.chargeImage(ad.id);
        this.chargeVideoUrl(ad.link);
      },
      error: (error: any) => {
        console.log('error al cargar los datos del anuncio ',error);
        this.auth.validate(error);
      }
    });
  }

  private simpleCall():void {

    this.service.getRandomAd(this.type, this.url).subscribe({
      
      next: (ad: Ad) => {
        this.ad = ad;
        this.chargeImage(ad.id);
        this.chargeVideoUrl(ad.link);
      },
      error: (error: any) => {
        console.log('error al cargar los datos del anuncio ',error);
        this.auth.validate(error);
      }
    });
  }

  chargeVideoUrl(link: string): void {
    if(this.ad.isVideo){
      this.videoUrl = this.sanitizer.bypassSecurityTrustResourceUrl(link);
    }
  }

  chargeImage(id: number): void {
    if(this.ad.isImage){
      
      this.fileService.getImage(id).subscribe({
        next: (blob: Blob) => {
          const objectUrl = URL.createObjectURL(blob);
          this.imageUrl = this.sanitizer.bypassSecurityTrustUrl(objectUrl);
          },
          error: (error: any) => {
            this.auth.validate(error);
            console.log('Error al cargar la imagen del anuncio: ',error);
          }
      });

    }
  }

}
