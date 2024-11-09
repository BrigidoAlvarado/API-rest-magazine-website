import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Ad } from '../../../entities/ad/ad';
import { AuthService } from '../../../services/auth';
import { VideoAdService } from '../../../services/ad/video-ad-service';
import { VideoAd } from '../../../entities/ad/video-ad';

@Component({
  selector: 'app-edit-video-ad-form',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './edit-video-ad-form.component.html',
  styleUrl: './edit-video-ad-form.component.css'
})
export class EditVideoAdFormComponent implements OnInit {
  @Input({required: true})
  ad!: Ad
  form!: FormGroup
  videoAd!: VideoAd

  constructor(
    private auth: AuthService,
    private service: VideoAdService,
    private formBuilder: FormBuilder
  ){}

  ngOnInit(): void {
    this.service.getVideoAdById(this.ad.id).subscribe({
      next: (videoAd: VideoAd) => {
        this.videoAd = videoAd;
        this.form = this.formBuilder.group({
          text: [ this.videoAd.text, [Validators.required]],
          link: [ this.videoAd.link, [Validators.required]]
        });
      },
      error: (error: any) => {
        console.log('error al cargar la informacion del anuncio de video con id ',error);
        this.auth.validate(error);
      }
    });
    this.form = this.formBuilder.group({
      text: [ this.videoAd.text, [Validators.required]],
      link: [ this.videoAd.link, [Validators.required]]
    });
  }

  submit(): void {
    this.videoAd = this.form.value as VideoAd;
    this.videoAd.id = this.ad.id;
    this.service.updateVideoAd(this.videoAd).subscribe({
      next: () => {
        window.alert('se actualizo el anuncio exitosamente');
      },
      error: (error: any) => {
        console.log('Error al actualizar el anuncio de video', error);
        this.auth.validate(error);
      }
    });
  }
}
