import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { LoginService } from '../../../services/login-service';
import { UserCredential} from '../../../entities/credential';

@Component({
  selector: 'app-login-form',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule,],
  templateUrl: './login-form.component.html',
  styleUrl: './login-form.component.css'
})
export class LoginFormComponent implements OnInit {
  loginForm!: FormGroup;
  userCredential!: UserCredential;
  created: boolean = false;

  constructor (private formBuilder: FormBuilder, private loginService: LoginService){

  }

  ngOnInit(): void {
      this.loginForm = this.formBuilder.group(
        {
          userType: [null, [Validators.required]],
          userName: [null, [Validators.required]],
          password: [null, [Validators.required]]
        }
      );
  }

  submit(): void {
    if(this.loginForm.valid){
      this.userCredential = this.loginForm.value as UserCredential;
      this.userCredential.password = btoa(this.userCredential.password);
      this.loginService.doLogin(this.userCredential).subscribe({
        next: () => {
          this.created = true;
          console.log('se econtro la sesion')
        },
        error: (error: any) => {
          console.log(error);
          this.created = false;
        }
      })
    }
  }
}
