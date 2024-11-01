import { Injectable } from "@angular/core";
import { RestConstants } from "./rest-constants";
import { HttpClient } from "@angular/common/http";
import { AuthService } from "./auth";
import { Magazine } from "../entities/magazine";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class EditorService {
    constants!: RestConstants;

    constructor(private http: HttpClient, private auth: AuthService){
        this.constants = new RestConstants();
    }

    getPublihsedMagazines(): Observable<Magazine []> {
        const headers = this.auth.getHeader();
        return this.http.get<Magazine []>(this.constants.API_URL+'editor', { headers });
    }

    postNewNumber(id: number, file:File): Observable<void> {
        const headers = this.auth.getHeader();

        let formData = new FormData();
        formData.append('id',`${id}`);
        formData.set('file',file,file.name);

        return this.http.post<void>(this.constants.API_URL+'editor',formData, { headers });
    }
}