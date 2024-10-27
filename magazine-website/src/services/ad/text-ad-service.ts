import { Injectable } from "@angular/core";
import { RestConstants } from "../rest-constants";
import { AuthService } from "../auth";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { TextAd } from "../../entities/ad/text-ad";

@Injectable({
    providedIn: 'root'
})
export class TextAdService{

    restConstants!: RestConstants;

    constructor(private auth: AuthService, private http: HttpClient){
        this.restConstants = new RestConstants();
    }

    getAdById(id: number): Observable<TextAd>{
        const headers = this.auth.getHeader();
        return this.http.get<TextAd>(this.restConstants.API_URL+'text-ad/'+id, { headers });
    }

    updateTextAd(textAd: TextAd): Observable<void> {
        const headers = this.auth.getHeader();
        return this.http.post<void>(this.restConstants.API_URL+"text-ad",textAd, { headers });
    }

}