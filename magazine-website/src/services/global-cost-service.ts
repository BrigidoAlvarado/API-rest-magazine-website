import { Injectable } from "@angular/core";
import { RestConstants } from "./rest-constants";
import { HttpClient } from "@angular/common/http";
import { AuthService } from "./auth";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class GlobalCostService {
    constants!: RestConstants;
    URL!: string;

    constructor(private http: HttpClient, private auth: AuthService){
        this.constants = new RestConstants();
        this.URL = `${this.constants.API_URL}global-cost`;
    }

    updateGlobalCost (cost: number, kind: string): Observable<void> {
        const headers = this.auth.getHeader();
        return this.http.post<void>(`${this.URL}/${kind}`, cost, { headers });
    }
}