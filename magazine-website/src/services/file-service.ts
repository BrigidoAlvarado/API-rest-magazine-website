import { Injectable } from "@angular/core";
import { RestConstants } from "./rest-constants";
import { HttpClient } from "@angular/common/http";
import { AuthService } from "./auth";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class FileService {

    constants!: RestConstants;

    constructor(private http: HttpClient, private auth: AuthService){
        this.constants = new RestConstants();
    }

    getPhoto(userName: string, userType: string): Observable<Blob> {
        const headers = this.auth.getHeader();
        return this.http.get(`${this.constants.API_URL}file/photo/${userName}/${userType}`, { 
            headers,
            responseType: 'blob' });
    }
    
}