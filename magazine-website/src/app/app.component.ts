import { Component, OnInit } from '@angular/core';
import { Event, NavigationEnd, Router, RouterLink ,RouterLinkActive, RouterOutlet } from '@angular/router';
import { LoginComponent } from '../app/user/login/login.component';
import { FooterComponent } from "./footer/footer.component";
import { ShowAdComponent } from "./ad/show-ad/show-ad.component";
import { filter } from 'rxjs';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterLink,
    RouterLinkActive,
    RouterOutlet,
    LoginComponent,
    FooterComponent,
    ShowAdComponent
],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
   
})
export class AppComponent {
  tittle = 'Pagina Web de Revistas';
}
