import { HttpClient } from "@angular/common/http";
import { RestConstants } from "../rest-constants";
import { Observable } from "rxjs";
import { Profile } from "../../entities/profile";
import { AuthService } from '../auth';
import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
})
export class ProfileService{
    restConstants: RestConstants;

    constructor(private httpClient: HttpClient, private authServive: AuthService){
        this.restConstants = new RestConstants();
    }

    public editProfile(profile: Profile): Observable<any>{
        const headers = this.authServive.getHeader();
        let formData: FormData = new FormData();
        formData.append('userName', profile.userName);
        formData.append('tastes', profile.tastes);
        formData.append('topicOfInterest', profile.topicOfInterest);
        formData.append('description', profile.description);
        formData.append('hobbies', profile.hobbies);
        if(profile.photo != null){
            formData.set('photo', profile.photo, profile.photo.name);
        }
        return this.httpClient.post<any>(this.restConstants.API_URL+'profile',formData,{ headers });
    }

    public getProfile(userName: string| null, userType:string | null): Observable<Profile>{
        const headers = this.authServive.getHeader();
        return this.httpClient.get<Profile>(`${this.restConstants.API_URL}profile/${userName}/${userType}`, { headers });
    }           
}