import { Injectable } from "@angular/core";
import { RestConstants } from "./rest-constants";
import { HttpClient } from "@angular/common/http";
import { Ad } from "../entities/ad/ad";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class ExportService {
    constants!: RestConstants;
    url!: string

    constructor(
        private http: HttpClient
    ){
        this.constants = new RestConstants();
        this.url = `${this.constants.API_URL}export/`;
    }

    exportAdminAd(adList: Ad[]): Observable < Blob > {
        return this.http.post(`${this.url}admin-ad`, adList,
            { 
                  responseType: 'blob'
            });
    }
}