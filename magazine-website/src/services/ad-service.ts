import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Amount } from "../entities/amount";
import { RestConstants } from "./rest-constants";
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

    getAdList(): Observable < Ad[] > {
        const headers = this.auth.getHeader();
        return this.http.get < Ad [] > (`${this.restConstants.API_URL}ad/ad-list`, { headers });
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
        return this.http.get<Ad>(this.restConstants.API_URL+'ad/'+id);
    }

    updateStatus(ad:Ad): Observable<void> {
        const headers = this.auth.getHeader();
        return this.http.post<void>(this.restConstants.API_URL+'ad',ad, { headers });
    }

    getRandomAd(type: string, url: string): Observable<Ad> {
        return this.http.get<Ad>(`${this.restConstants.API_URL}ad/random/${type}/${url}`);
    }

    getRandomAdWhitEditor(type: string, url: string, editor: string): Observable<Ad> {
        return this.http.get<Ad>(`${this.restConstants.API_URL}ad/random/${type}/${url} /${editor}`);
    }
}