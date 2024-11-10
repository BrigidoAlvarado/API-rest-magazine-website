import { HttpClient } from "@angular/common/http";
import { AuthService } from "../auth";
import { RestConstants } from "../rest-constants";
import { Observable } from "rxjs";
import { Magazine } from "../../entities/magazine";
import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
})
export class MagazineService {
    constants: RestConstants;

    constructor(private http:HttpClient, private auth: AuthService){
        this.constants = new RestConstants();
    }

    post(magazine: Magazine): Observable<void> {
        const headers = this.auth.getHeader();
        let formData = new FormData();
        formData.append('title',magazine.tittle);
        formData.append('category',magazine.category);
        formData.append('description',magazine.description);
        formData.append('tags',magazine.tags);
        formData.append('date',magazine.date);
        formData.set('magazine',magazine.file, magazine.file.name);
        console.log('enviando revista')
        
        return this.http.post<void>(this.constants.API_URL+'magazine', formData, { headers });
    }

    getMagazineById(id: number): Observable<Magazine> {
        const headers = this.auth.getHeader();
        return this.http.get<Magazine>(`${this.constants.API_URL}magazine/id/${id}`,{ headers });
    }

    getMagazineList(): Observable<Magazine []> {
        const headers = this.auth.getHeader();
        return this.http.get<Magazine[]>(this.constants.API_URL+'magazine', { headers });
    }

    getNewMagazineList(): Observable<Magazine []> {
        const headers = this.auth.getHeader();
        return this.http.get<Magazine []>(this.constants.API_URL+'magazine/new', { headers });
    }

    setCost(magazine: Magazine): Observable<void> {
        const headers = this.auth.getHeader();
        return this.http.post<void>(this.constants.API_URL+'magazine/cost',magazine, { headers });
    }
}