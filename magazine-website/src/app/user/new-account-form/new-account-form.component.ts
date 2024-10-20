import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Account} from '../../../entities/account';
import { InfoFileResponse} from '../../../entities/infoFileResponse'
import { NewAccountService} from '../../../services/new-account-service';
import { UsersHome } from '../../../services/users-home';

@Component({
  selector: 'app-new-account-form',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule,],
  templateUrl: './new-account-form.component.html',
  styleUrl: './new-account-form.component.css'
})
export class NewAccountFormComponent implements OnInit {

  uploadFileForm!: FormGroup;
  accepted: boolean = true;
  infoFileResponse!: InfoFileResponse;
  userType!: string;

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
      })
  }

  

  submit(): void {
    if(this.uploadFileForm.valid){
      let account = this.uploadFileForm.value as Account;
      this.newAccountService.uploadFile(account).subscribe({
        next:(response) => {
          this.accepted = true;
          localStorage.setItem('authToken',response.token);
          this.usersHome.redirect(this.userType);
          this.uploadFileForm.reset();
        },
        error: (error: any) => {
          console.log(error);
          this.accepted = false;
        }
      });
    }
  }


}
