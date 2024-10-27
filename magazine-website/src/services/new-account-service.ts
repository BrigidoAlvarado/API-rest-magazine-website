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
        let form = new FormData;
        form.append('userName',account.userName);
        form.append('userType',account.userType);
        form.append('password',btoa(account.password));
        form.append('description',account.description);
        form.append('hobbies',account.description);
        form.append('tastes',account.tastes);
        form.append('topicsOfInterest',account.topicOfInterest);
        if(account.photo != null){
            form.set('photo',account.photo,account.photo.name);
        }
        return this.httpClient.post<{ token: string }>(this.restConstants.API_URL+'new-account', form);
    }
}