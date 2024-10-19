import { HttpClient } from "@angular/common/http";
import { RestConstants } from "../rest-constants";
import { Observable } from "rxjs";
import { Profile } from "../../entities/profile";

export class ProfileService{
    restConstants: RestConstants;

    constructor(private httpClient: HttpClient){
        this.restConstants = new RestConstants();
    }

    public getProfile(userName: string): Observable<Profile>{
        const url = `${this.restConstants.API_URL}/${userName}`;
        return this.httpClient.get<Profile>(url);
    }
}