import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Amount } from "../entities/amount";
import { RestConstants } from "./rest-constants";
import { TextAd } from "../entities/ad/text-ad";
import { AuthService } from "./auth";
import { Ad } from "../entities/ad/ad";

@Injectable({
    providedIn: 'root'
})
export class AdService {

    restConstants: RestConstants;

    constructor(private http:HttpClient, private auth: AuthService){
        this.restConstants = new RestConstants();
    }

    getCost(kind: string): Observable<Amount>{
        return this.http.get<Amount>(this.restConstants.API_URL+'global-cost/'+kind);
    }

    getAds(): Observable<Ad[]> {
        const headers = this.auth.getHeader();
        return this.http.get<Ad[]>(this.restConstants.API_URL+"ad",{ headers });
    }

    getAdById(id: number): Observable<Ad> {
        const headers = this.auth.getHeader();
        return this.http.get<Ad>(this.restConstants.API_URL+'ad/'+id, { headers });
    }

    updateStatus(ad:Ad): Observable<void> {
        const headers = this.auth.getHeader();
        return this.http.post<void>(this.restConstants.API_URL+'ad',ad, { headers });
    }

    postTextAd(textAd: TextAd): Observable<Amount>{
        const headers = this.auth.getHeader();
        return this.http.post<Amount>(this.restConstants.API_URL+'text-ad',textAd,{ headers });
    }
}