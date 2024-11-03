  import { HttpHeaders } from "@angular/common/http";
  import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
  import { jwtDecode } from "jwt-decode";

  @Injectable({
    providedIn: 'root'
  })

  export class AuthService{

    constructor(private router: Router){}

    private getToken(): string | null {
      return localStorage.getItem('authToken');
    }

    validate(error: any): void {
      if(error.status === 401){
        localStorage.clear;
        window.alert('Sesion invalida!!');
        this.router.navigate(['/login']);
      }
      this.validateWithOutRole(error);
    }

    validateWithOutRole(error: any):void {
       if (error.status === 403){
        window.alert('El servidor no tiene la informacin necesaria para procesar la peticion \n codigo de error: '+error.status);
      } else if ( error.status === 400){
        window.alert('Se recibio informacion que el servidor no puede procesar\n codigo de error: '+error.status);
      } else if(error.status === 500) {
        window.alert('Ha ocurrido un error en el servidor \n por favor intentalo de nuevo mas tarde \n codigo de error: '+error.status);
      } else {
        window.alert('Ha ocurrido un error inesperado por favor intentalo de nuevo mas tarde \n codigo de error: '+error.status);
      }
    }

    validateMessage(error: any,mss: string): void {
      if(error.status === 401){
        localStorage.clear;
        window.alert('Sesion invalida!!');
        this.router.navigate(['/login']);
      } else if (error.status === 403){
        window.alert('El servidor no tiene la informacin necesaria para procesar la peticion \n'+mss+'\n codigo de error: '+error.status);
      } else if ( error.status === 400){
        window.alert('Se recibio informacion que el servidor no puede procesar\n'+mss+'\n codigo de error: '+error.status);
      } else if(error.status === 500) {
        window.alert('Ha ocurrido un error en el servidor \n por favor intentalo de nuevo mas tarde \n codigo de error: '+error.status);
      } else {
        window.alert('Ha ocurrido un error inesperado por favor intentalo de nuevo mas tarde \n codigo de error: '+error.status);
      }
    }

    public getHeader(): HttpHeaders {
      const authToken = this.getToken();
      let headers = new HttpHeaders();
      if(authToken){
        headers = headers.append(`Authorization`,`Bearer ${authToken}`);
      }
      return headers;
    }

    public getUserName(): string | null{
      const token = this.getToken();
      if(token){
        try {
          const decodedToken: any = jwtDecode(token);
          return decodedToken.sub;
        } catch (error) {
          console.error('Error al decodificar el token',error);
          return null;
        }
      }
      return null;
    }

    public getUserType(): string | null {
      const token = this.getToken();
      if(token) {
        try{
          const decodedToken: any = jwtDecode(token);
          return decodedToken.userType;
        } catch (error) {
          console.error('Error al decodificar el token', error);
          return null;
        }
      }
      return null;
    }
  }