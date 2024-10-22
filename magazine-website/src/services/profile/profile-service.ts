import { HttpClient } from "@angular/common/http";
import { RestConstants } from "../rest-constants";
import { Observable } from "rxjs";
import { Profile } from "../../entities/profile";
import { AuthService } from '../auth';
import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
})
export class ProfileService{
    restConstants: RestConstants;

    constructor(private httpClient: HttpClient, private authServive: AuthService){
        this.restConstants = new RestConstants();
    }

    public getProfile(): Observable<Profile>{
        const headers = this.authServive.getHeader();
        return this.httpClient.post<Profile>(this.restConstants.API_URL+'profile',{headers});
    }           
}