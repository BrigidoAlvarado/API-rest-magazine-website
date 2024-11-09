import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Ad } from '../../../entities/ad/ad';
import { ImageAd } from '../../../entities/ad/image-ad';
import { AuthService } from '../../../services/auth';
import { ImageAdService } from '../../../services/ad/image-ad-service';

@Component({
  selector: 'app-edit-image-ad-form',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './edit-image-ad-form.component.html',
  styleUrl: './edit-image-ad-form.component.css'
})
export class EditImageAdFormComponent implements OnInit {

  @Input({required: true})
  ad!: Ad
  imageAd!: ImageAd
  form!: FormGroup
  selectedFile!: File

  constructor(
    private auth: AuthService,
    private service: ImageAdService,
    private formBuilder: FormBuilder
  ){}

  ngOnInit(): void {

    this.service.getImageAdById(this.ad.id).subscribe({
      next: (imageAd: ImageAd) => {
        this.imageAd = imageAd;
        this.form = this.formBuilder.group({
          text: [this.imageAd.text, [Validators.required]],
          image: [null , [Validators.required]]
        }); 
      },
      error: (error: any) => {
        console.log('error al cargar el anuncio ',error);
        this.auth.validate(error);
      }
    })
    this.form = this.formBuilder.group({
      text: [this.imageAd.text, [Validators.required]],
      image: [ null, [Validators.required]]
    });

  }

  submit(): void {
    this.imageAd = this.form.value as ImageAd;
    this.imageAd.image = this.selectedFile;
    this.imageAd.id = this.ad.id;
    this.service.updateAd(this.imageAd).subscribe({
      next: () => {
        window.alert('Actualizacion exitosa');
      },
      error: (error: any) => {
        console.log('error al actualizar el anuncio',error);
        this.auth.validate(error);
      }
    })

  }

  onFileChange(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      const file = input.files[0];
      this.selectedFile = file;
    }
  }
}
