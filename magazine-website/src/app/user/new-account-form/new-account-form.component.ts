import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Account} from '../../../entities/account';
import { NewAccountService} from '../../../services/new-account-service';
import { UsersHome } from '../../../services/users-home';

@Component({
  selector: 'app-new-account-form',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './new-account-form.component.html',
  styleUrl: './new-account-form.component.css'
})
export class NewAccountFormComponent implements OnInit {

  uploadFileForm!: FormGroup;
  accepted: boolean = true;
  selectedFile: File | null = null; // Mantén una referencia al archivo seleccionado

  constructor(private formBuilder: FormBuilder, private newAccountService: NewAccountService, private usersHome: UsersHome){}
  
  ngOnInit(): void {
    this.uploadFileForm = this.formBuilder.group({
      userType: [null, [Validators.required]],
      userName: [null, [Validators.required]],
      password: [null, [Validators.required]],
      tastes: [null],
      topicOfInterest: [null],
      description: [null],
      hobbies: [null],
      photo: [null]
    });
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

  submit(): void {
    if (this.uploadFileForm.valid) {
      const formData = new FormData();

      // Agrega todos los campos al FormData excepto la foto
      Object.keys(this.uploadFileForm.controls).forEach(key => {
        const controlValue = this.uploadFileForm.get(key)?.value;
        formData.append(key, controlValue);
      });

      // Si se seleccionó un archivo, agrégalo al FormData
      if (this.selectedFile instanceof File) {
        console.log(this.selectedFile);
        console.log('Tipo MIME del archivo despues del append:', this.selectedFile.type, this.selectedFile.name);
        formData.set('photo', this.selectedFile, this.selectedFile.name);
        console.log('form data: ',formData.get('photo'));
        const photo = formData.get('photo');
        if (photo instanceof File) {
        console.log('El archivo "photo" es de clase File');
        console.log('Nombre del archivo:', photo.name);
        console.log('Tipo de archivo:', photo.type);
        console.log('Tamaño del archivo:', photo.size);
        } else {
        console.log('(antes de entrar al servicio)El archivo "photo" no es de clase File o no está presente');
        }
      }

        const photo = formData.get('photo');
        if (photo instanceof File) {
        console.log('El archivo "photo" es de clase File');
        console.log('Nombre del archivo:', photo.name);
        console.log('Tipo de archivo:', photo.type);
        console.log('Tamaño del archivo:', photo.size);
        } else {
        console.log('(antes de entrar al servicio)El archivo "photo" no es de clase File o no está presente');
        }

      this.newAccountService.uploadFile(formData).subscribe({


        next: (response) => {
          this.accepted = true;
          localStorage.setItem('authToken', response.token);
          this.usersHome.redirect(this.uploadFileForm.get('userType')?.value);
          this.uploadFileForm.reset();
          this.selectedFile = null; // Limpia la referencia del archivo
        },
        error: (error: any) => {
          console.log(error);
          this.accepted = false;
        }
      });
    }
  }
}
