import { Injectable } from "@angular/core";
import { RestConstants } from "../rest-constants";
import { HttpClient } from "@angular/common/http";
import { AuthService } from "../auth";
import { VideoAd } from "../../entities/ad/video-ad";
import { Observable } from "rxjs";
import { Amount } from "../../entities/amount";

@Injectable({
    providedIn: 'root'
})

export class VideoAdService {
    constants!: RestConstants;
    URL!: string;

    constructor(private http: HttpClient, private auth: AuthService){
        this.constants = new RestConstants();
        this.URL = `${this.constants.API_URL}video-ad`;
    }

    buyVideoAd(videoAd: VideoAd): Observable<Amount> {
        const headers = this.auth.getHeader();
        return this.http.post<Amount>(`${this.URL}/buy`, videoAd, { headers });
    }
}