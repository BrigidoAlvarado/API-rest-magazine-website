import { Injectable } from "@angular/core";
import { RestConstants } from "../rest-constants";
import { AuthService } from "../auth";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Amount } from "../../entities/amount";
import { ImageAd } from "../../entities/ad/image-ad";

@Injectable({
    providedIn: 'root'
})
export class ImageAdService{

    constants!: RestConstants;
    URL!: string;

    constructor(private http: HttpClient, private auth: AuthService){
        this.constants = new RestConstants();
        this.URL = `${this.constants.API_URL}image-ad`;
    }

    buyImageAd(imageAd: ImageAd): Observable<Amount> {
        const headers = this.auth.getHeader();

        let formData = new FormData;
        formData.append('kind', imageAd.kind);
        formData.append('time', imageAd.time);
        formData.append('text', imageAd.text);
        formData.append('date', imageAd.date.toString());
        formData.set('image', imageAd.image, imageAd.image.name);

        return this.http.post<Amount>(`${this.URL}/buy`, formData, { headers });
    }

    getImageAdById(id: number): Observable< ImageAd > {
        const headers = this.auth.getHeader();
        return this.http.get< ImageAd > (`${this.URL}/get/${id}`, { headers });
    }

    updateAd(imageAd: ImageAd): Observable< void > {
        const headers = this.auth.getHeader();
        let formData = new FormData;
        formData.append('text', imageAd.text);
        formData.append('id', imageAd.id.toString());
        if(imageAd.image){
            formData.set('image', imageAd.image, imageAd.image.name);
        }
        return this.http.post< void > (`${this.URL}/update`, formData, { headers });
    }
}