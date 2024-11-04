import { Injectable } from "@angular/core";
import { RestConstants } from "./rest-constants";
import { HttpClient } from "@angular/common/http";
import { AuthService } from "./auth";
import { Observable } from "rxjs";
import { Magazine } from "../entities/magazine";

@Injectable({
    providedIn: 'root'
})
export class AdminService {

    constants!: RestConstants;
    URL!: string;

    constructor(private http: HttpClient, private auth: AuthService){
        this.constants = new RestConstants();
        this.URL = `${this.constants.API_URL}admin`;
    }

    getMagazineList(): Observable<Magazine []> {
        const headers = this.auth.getHeader();
        return this.http.get<Magazine[]> (`${this.URL}/magazine`, { headers });
    }

    updateDailyCost( magazine: Magazine): Observable<void> {
        const headers = this.auth.getHeader();
        return this.http.post<void>(`${this.URL}/cost`, magazine, { headers });
    }
}