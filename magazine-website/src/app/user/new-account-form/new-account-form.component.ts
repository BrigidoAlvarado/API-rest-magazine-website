import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { NewAccountService} from '../../../services/new-account-service';
import { UsersHome } from '../../../services/users-home';
import { Account } from '../../../entities/account';

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
  selectedFile: File | null = null;

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
      let account = this.uploadFileForm?.value as Account;
      //aniadir el archivo a la cuenta
      if(this.selectedFile instanceof File){
        account.photo = this.selectedFile;
      }
      this.newAccountService.uploadFile(account).subscribe({
        next: (response) => {
          this.accepted = true;
          localStorage.setItem('authToken', response.token);
          this.usersHome.redirect(this.uploadFileForm.get('userType')?.value);
          this.uploadFileForm.reset();
          this.selectedFile = null;
        },
        error: (error: any) => {
          console.log(error);
          this.accepted = false;
        }
      });
    }
  }
}
