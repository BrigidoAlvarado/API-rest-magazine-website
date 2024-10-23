  import { HttpHeaders } from "@angular/common/http";
  import { Injectable } from "@angular/core";
  import { jwtDecode } from "jwt-decode";

  @Injectable({
    providedIn: 'root'
  })

  export class AuthService{

    constructor(){}

    private getToken(): string | null {
      return localStorage.getItem('authToken');
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