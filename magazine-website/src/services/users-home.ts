import { Router } from "@angular/router";
import { Injectable } from "@angular/core";

@Injectable({
  providedIn: 'root'
})

export class UsersHome {

    constructor(private router: Router){}

    redirect(userType: string): void {
        switch (userType) {
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