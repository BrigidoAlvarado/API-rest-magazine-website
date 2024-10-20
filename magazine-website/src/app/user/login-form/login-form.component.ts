import { Component, OnInit, ɵdetectChangesInViewIfRequired } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { LoginService } from '../../../services/login-service';
import { UserCredential} from '../../../entities/credential';
import { Router } from '@angular/router';

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

  constructor (private formBuilder: FormBuilder, private loginService: LoginService, private router: Router){  }

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
          this.created = true;
          localStorage.setItem('authToken',response.token);
    
          //redifigir a la vista que corresponde a su rol
          console.log('HOLA MUNDO');
          this.redirect();
          this.loginForm.reset();
          console.log('sesion iniciada con exito');
        },  
        error: (error: any) => {
          console.log(error);
          this.created = false;
        }
      })
    }
  }

  redirect(){
    switch (this.userCredential.userType) {
      case 'admin':
        this.router.navigate(['/admin-home']);
        break;
      case 'advertiser':
        this.router.navigate(['/advertiser-home']);
        break;
      case 'editor':
        this.router.navigate(['/editor-home']);
        break;
      case 'subscriber':
        this.router.navigate(['/subscriber-home']);
        break;
      default:
        console.log('Tipo de usuario desconocido');
        break;
    }
  }
}
