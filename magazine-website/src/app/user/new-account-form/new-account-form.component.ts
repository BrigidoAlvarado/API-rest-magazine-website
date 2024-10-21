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

  submit(): void {
    if (this.uploadFileForm.valid) {
      const formData = new FormData();
      
      // Agregar todos los campos al FormData
      Object.keys(this.uploadFileForm.controls).forEach(key => {
        const controlValue = this.uploadFileForm.get(key)?.value;
        formData.append(key, controlValue);
      });
      
      this.newAccountService.uploadFile(formData).subscribe({
        next: (response) => {
          this.accepted = true;
          localStorage.setItem('authToken', response.token);
          this.usersHome.redirect(this.uploadFileForm.get('userType')?.value);
          this.uploadFileForm.reset();
        },
        error: (error: any) => {
          console.log(error);
          this.accepted = false;
        }
      });
    }
  }


  onFileChange(event: Event): void {
  const input = event.target as HTMLInputElement;
  if (input.files && input.files.length > 0) {
    const file = input.files[0];
    this.uploadFileForm.patchValue({
      photo: file
    });
  } else {
    // Si no hay archivo seleccionado, se establece el campo 'photo' en null
    this.uploadFileForm.patchValue({
      photo: null
    });
  }
   this.uploadFileForm.get('photo')?.updateValueAndValidity();
  }
  
}