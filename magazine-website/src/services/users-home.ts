import { Router } from "@angular/router";
import { Injectable } from "@angular/core";
import { AuthService } from '../services/auth';

@Injectable({
  providedIn: 'root'
})

export class UsersHome {

    constructor(private router: Router, private auth: AuthService){}

    redirect(userType: string | null): void {

      if(userType === null){
        userType = this.auth.getUserType();
        console.log(userType);
      }
        switch (userType) {
          case 'admin':
            console.log('redirigiendo admin');
            this.router.navigate(['/admin-home']);
            break;
          case 'anunciante':
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
    
      redirectToProfile(userType: string | null): void {
        switch (userType) {
          case 'admin':
            this.router.navigate(['/admin-profile']);
            break;
          case 'anunciante':
            this.router.navigate(['/advertiser-profile']);
            break;
          case 'editor':
            this.router.navigate(['/editor-profile']);
            break;
          case 'subscriber':
            this.router.navigate(['/subscriber-profile']);
            break;
          default:
            //cerrar sesion y redirigir al login
            console.log('Tipo de usuario desconocido');
            break;
        }
      }

      redirectToEditProfile(): void {
        let userType = this.auth.getUserType();
        console.log('el tipo de usuario es: ',userType);
        switch (userType) {
          case 'admin':
            this.router.navigate(['/admin-edit-profile']);
            break;
          case 'anunciante':
            this.router.navigate(['/advertiser-edit-profile']);
            break;
          case 'editor':
            this.router.navigate(['/editor-edit-profile']);
            break;
          case 'subscriber':
            this.router.navigate(['/subscriber-edit-profile']);
            break;
          default:
            //cerrar sesion y redirigir al login
            console.log('Tipo de usuario desconocido');
            break;
        }
      }
}