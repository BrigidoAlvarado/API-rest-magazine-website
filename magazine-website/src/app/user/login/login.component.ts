import { Component } from '@angular/core';
import { LoginFormComponent } from "../login-form/login-form.component";
import { ShowAdComponent } from "../../ad/show-ad/show-ad.component";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [LoginFormComponent, ShowAdComponent],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  url: string = 'login';
  
}
