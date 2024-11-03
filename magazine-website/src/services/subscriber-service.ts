import { Injectable } from "@angular/core";
import { RestConstants } from "./rest-constants";
import { HttpClient } from "@angular/common/http";
import { AuthService } from "./auth";
import { Observable } from "rxjs";
import { Magazine } from "../entities/magazine";

@Injectable({
    providedIn: 'root'
})
export class SubscriberService {
    constants: RestConstants;
    URL!: string;

    constructor(private http: HttpClient, private auth: AuthService){
        this.constants = new RestConstants();
        this.URL = this.constants.API_URL+'subscriber';
    }

    like(id: number): Observable<void> {
        const headers = this.auth.getHeader();
        return this.http.post<void>(`${this.URL}/like`, id, { headers });
    }

    comment(id: number, comment: String): Observable<void> {
        const headers = this.auth.getHeader();
        return this.http.post<void>(`${this.URL}/comment/${id}`,comment,{ headers });
    }

    getSubscribedMagazineById(id: number): Observable<Magazine> {
        const headers = this.auth.getHeader();
        return this.http.get<Magazine>(`${this.constants.API_URL}subscriber/magazine/${id}`, { headers });
    }

    getSubscribedMagazineList(): Observable <Magazine []> {
        const headers = this.auth.getHeader();
        return this.http.get<Magazine []>(`${this.constants.API_URL}subscriber/magazine`, { headers });
    }

    getExplorerMagazines(categoryFilter: string, tagFilter: string): Observable<Magazine []> {
        if(categoryFilter === ''){
            categoryFilter = 'void';
        }
        if(tagFilter === ''){
            tagFilter = 'void';
        }
        const headers = this.auth.getHeader();
        return this.http.get<Magazine []>(`${this.constants.API_URL}subscriber/explorer/${categoryFilter}/${tagFilter}`,{ headers });
    }

    subscribe(magazine: Magazine): Observable<void> {
        const headers = this.auth.getHeader();
        return this.http.post<void>(`${this.constants.API_URL}subscriber/subscribe`,magazine, { headers });
    }
}   