import { Injectable } from "@angular/core";
import { RestConstants } from "./rest-constants";
import { HttpClient } from "@angular/common/http";
import { AuthService } from "./auth";
import { Filter } from "../entities/filter";
import { Observable } from "rxjs";
import { LockAd } from "../entities/lockAd";
import { Magazine } from "../entities/magazine";

@Injectable({
    providedIn: 'root'
})
export class EditorReportsService {
    contants!: RestConstants;
    URL!: string;

    constructor(
        private http: HttpClient,
        private auth: AuthService
    ){
        this.contants = new RestConstants();
        this.URL = `${this.contants.API_URL}editor-reports/`;
    }

    getPaymentReport( filter: Filter): Observable < LockAd[] > {
        const headers = this.auth.getHeader();
        return this.http.post< LockAd [] > (`${this.URL}payment`, filter, { headers });
    }

    getFavoriteMagazineReport(filter: Filter): Observable < Magazine [] > {
        const headers = this.auth.getHeader();
        return this.http.post < Magazine[] > (`${this.URL}favorite`, filter, { headers });
    }

    getSubscriptionReport(filter: Filter): Observable < Magazine [] > {
        const headers = this.auth.getHeader();
        return this.http.post < Magazine [] > (`${this.URL}subscription`, filter , { headers });
    }

    getCommentsReport( filter: Filter): Observable < Magazine [] > {
        const headers = this.auth.getHeader();
        return this.http.post< Magazine [] > (`${this.URL}comment`, filter, { headers });
    }
}