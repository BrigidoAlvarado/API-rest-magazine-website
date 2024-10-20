import { Injectable } from "@angular/core";
import { RestConstants } from './rest-constants';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Account} from '../entities/account'

@Injectable({
    providedIn: 'root'
})

export class NewAccountService{
    restConstants: RestConstants;
    
    constructor(private httpClient: HttpClient){
        this.restConstants = new RestConstants( );
    }

    uploadFile(account: Account): Observable<{ token: string }>{
        let formData: FormData = new FormData();
        formData.append('userType', account.userType);
        formData.append('userName', account.userName);
        formData.append('password', btoa(account.password));
        formData.append('tastes', account.tastes);
        formData.append('topicOfInterest', account.topicOfInterest);
        formData.append('description', account.description);
        formData.append('hobbies', account.hobbies);
        if(account.photo != null){
            formData.append('photo', account.photo, account.photo.name);
        } 
        return this.httpClient.post<{ token: string }>(this.restConstants.API_URL+'new-account', formData);
    }
}