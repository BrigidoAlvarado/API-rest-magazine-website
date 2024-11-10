import { Injectable } from "@angular/core";
import { RestConstants } from "./rest-constants";
import { HttpClient } from "@angular/common/http";
import { AuthService } from "./auth";
import { Observable } from "rxjs";
import { EarningReport } from "../entities/admin-report/earning-report";
import { Filter } from "../entities/filter";
import { Ad } from "../entities/ad/ad";
import { Advertiser } from "../entities/advertiser";
import { Magazine } from "../entities/magazine";
import { AdView } from "../entities/ad/adView";

@Injectable({
    providedIn: 'root'
})
export class AdminReportService {

    constants!: RestConstants;
    URL!: string;

    constructor(private http: HttpClient, private auth: AuthService){
        this.constants = new RestConstants();
        this.URL = `${this.constants.API_URL}admin-report`;
    }

    getEarningReport(): Observable<EarningReport> {
        const headers = this.auth.getHeader();
        return this.http.get<EarningReport>(`${this.URL}/earning`, { headers });
    }

    getAdReports(filter: Filter): Observable<Ad []> {
        const headers = this.auth.getHeader();
        return this.http.post<Ad[]>(`${this.URL}/ad`, filter, { headers });
    }

    getAdvertiserReport(userName: string): Observable<Advertiser []> {
        if(userName === null){userName = 'null'}
        const headers = this.auth.getHeader();
        return this.http.get<Advertiser []> (`${this.URL}/advertiser/${userName}`, { headers });
    }

    getPopularMagazineReport(filter: Filter): Observable<Magazine[]> {
        const headers = this.auth.getHeader();
        return this.http.post<Magazine[]> (`${this.URL}/popular`, filter, { headers });
    }

    getEffectivityAdReport( filter: Filter): Observable<Advertiser []> {
        const headers = this.auth.getHeader();
        return this.http.post<Advertiser  []> (`${this.URL}/effectivity-ad`, filter, { headers });
    }

    getCommentMagazineReport(filter: Filter): Observable< Magazine[] >{
        const headers = this.auth.getHeader();
        return this.http.post< Magazine[] > (`${this.URL}/comments`, filter, { headers });
    }
}