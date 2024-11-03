import { Injectable } from "@angular/core";
import { RestConstants } from "./rest-constants";
import { HttpClient } from "@angular/common/http";
import { AuthService } from "./auth";
import { Magazine } from "../entities/magazine";
import { Observable } from "rxjs";
import { LockAd } from "../entities/lockAd";
import { Amount } from "../entities/amount";

@Injectable({
    providedIn: 'root'
})
export class EditorService {
    constants!: RestConstants;
    URL!: string;

    constructor(private http: HttpClient, private auth: AuthService){
        this.constants = new RestConstants();
        this.URL = `${this.constants.API_URL}editor`;
    }

    updateCommentLikeStatus(magazine: Magazine): Observable<void> {
        const headers = this.auth.getHeader();
        return this.http.post<void>(`${this.URL}/status`, magazine, { headers });
    }

    buyLockAd(lockAd: LockAd): Observable<Amount> {
        const headers = this.auth.getHeader();
        return this.http.post<Amount> (`${this.URL}/lockAd`,lockAd, { headers });
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