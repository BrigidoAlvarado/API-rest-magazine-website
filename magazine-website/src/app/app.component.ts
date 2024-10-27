import { Component } from '@angular/core';
import { RouterLink ,RouterLinkActive, RouterOutlet } from '@angular/router';
import { LoginComponent } from '../app/user/login/login.component';
import { FooterComponent } from "./footer/footer.component";
//import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterLink, 
    RouterLinkActive, 
    RouterOutlet, 
    LoginComponent, 
    FooterComponent,
    /*BrowserAnimationsModule*/],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
   
})
export class AppComponent {
 tittle = 'Pagina Web de Revistas';
}
