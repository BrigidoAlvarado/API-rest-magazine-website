import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { RestConstants } from "./rest-constants";
import { Observable } from "rxjs";
import { UserCredential} from "../entities/credential"

@Injectable({
    providedIn: 'root'
}
)

export class LoginService{
    restConstants: RestConstants;
    constructor(private httpClient: HttpClient) { 
    this.restConstants = new RestConstants();
  }
    
    public doLogin(credential: UserCredential): Observable<void>{
        return this.httpClient.post<void>(this.restConstants.API_URL+'login',credential);
    }
}