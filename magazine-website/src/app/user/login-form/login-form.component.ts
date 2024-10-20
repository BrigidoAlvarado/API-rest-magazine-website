import { Component, OnInit, ɵdetectChangesInViewIfRequired } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { LoginService } from '../../../services/login-service';
import { UserCredential } from '../../../entities/credential';
import { UsersHome } from '../../../services/users-home';

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
  accepted: boolean = true;

  constructor (private formBuilder: FormBuilder, private loginService: LoginService, private usersHome: UsersHome){  }

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
        next: (response) => {
          this.accepted = true;
          localStorage.setItem('authToken',response.token);
    
          //redifigir a la vista que corresponde a su rol
          console.log('HOLA MUNDO');
          this.usersHome.redirect(this.userCredential.userType);
          this.loginForm.reset();
          console.log('sesion iniciada con exito');
        },  
        error: (error: any) => {
          console.log(error);
          this.accepted = false;
        }
      })
    }
  }

  
}
