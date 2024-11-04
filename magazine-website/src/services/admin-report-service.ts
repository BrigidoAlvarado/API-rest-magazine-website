import { Injectable } from "@angular/core";
import { RestConstants } from "./rest-constants";
import { HttpClient } from "@angular/common/http";
import { AuthService } from "./auth";
import { Observable } from "rxjs";
import { EarningReport } from "../entities/admin-report/earning-report";

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

}