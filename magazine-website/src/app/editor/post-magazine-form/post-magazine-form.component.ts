import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Magazine } from '../../../entities/magazine';
import { MagazineService } from '../../../services/editor-service/magazine-service';
import { AuthService } from '../../../services/auth';

@Component({
  selector: 'app-post-magazine-form',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './post-magazine-form.component.html',
  styleUrl: './post-magazine-form.component.css'
})
export class PostMagazineFormComponent implements OnInit{

  form!: FormGroup;
  selectedFile: File | null = null;

  constructor(
    private builder: FormBuilder, 
    private magazineService: MagazineService,
    private auth: AuthService){}

  ngOnInit(): void {

    this.form = this.builder.group({
      title: [null, [Validators.required, Validators.max(200)]],
      category: [null, [Validators.required, Validators.max(45)]],
      description: [null, [Validators.required, Validators.max(200)]],
      tags: [null, [Validators.required]],
      date: [null, [Validators.required]],
      magazine: [null, [Validators.required]]
    });
      
  }

  submit(): void {
    if(this.form.valid){
      let magazine = this.form?.value as Magazine;
      console.log('date :'+magazine.date);;
      if(this.selectedFile instanceof File){
        console.log(this.selectedFile);
        magazine.file = this.selectedFile;
        console.log(magazine.file);
      }
      this.magazineService.post(magazine).subscribe({
        next: () => {
          window.alert('La revista: \n'+magazine.tittle+'\n fue publicada exitosamente');
          this.form.reset();
        },
        error: (error: any) => {
          console.log(error);
          this.auth.validate(error);
        }
      });
    }
  }

  onFileChange(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      const file = input.files[0];
      console.log('Tipo MIME del archivo:', file.type);
      // Guarda el archivo en la referencia en lugar de actualizar el formulario directamente
      this.selectedFile = file;
    } else {
      this.selectedFile = null;
    }
  }
}
